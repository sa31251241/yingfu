package com.liaojun.webadmin.sale.service.impl;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.service.ResultBuilder;
import com.liaojun.component.base.util.IdGenerateUtil;
import com.liaojun.component.base.util.StringUtil;
import com.liaojun.component.base.util.ValidateUtil;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.base.constants.SysConstants;
import com.liaojun.webadmin.sale.mapper.SaleOrderMapper;
import com.liaojun.webadmin.sale.model.SaleOrder;
import com.liaojun.webadmin.sale.model.SaleOrderDetail;
import com.liaojun.webadmin.sale.service.SaleOrderDetailService;
import com.liaojun.webadmin.sale.service.SaleOrderService;
import com.liaojun.webadmin.stock.service.IInvDetailService;
import com.liaojun.webadmin.stock.service.IInvSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:36
 */
@Service
public class SaleOrderServiceImpl extends BaseServiceImpl<SaleOrder> implements SaleOrderService {
    @Override
    public Class getEntityClass() {
        return SaleOrder.class;
    }

    @Autowired
    private SaleOrderMapper saleOrderMapper;

    @Autowired
    private SaleOrderDetailService saleOrderDetailService;

    @Autowired
    private IInvSumService invSumService;

    @Autowired
    private IInvDetailService invDetailService;


    @Override
    public SaleOrder findById(String id) {
        return saleOrderMapper.findById(id);
    }



    @Override
    public Result saveOrUpdate(SaleOrder saleOrder) {
        boolean isNew = true;
        if(!StringUtil.isEmpty(saleOrder.getId())){
            isNew = false;
        }
        saleOrder.setSaleOrderDetailList(convertSaleOrderDetail(saleOrder.getSaleOrderDetailList()));
        Result checkResult = checkParams(saleOrder,isNew);
        if(!checkResult.isSuccess()){
            return checkResult;
        }
        setSumAttr(saleOrder);
        if(isNew){
            saveSaleOrder(saleOrder);
            return ResultBuilder.saveSuccessResult();
        }else{
            updateSaleOrder(saleOrder);
            return ResultBuilder.saveSuccessResult();
        }
    }

    @Override
    @Transactional
    public Result deleteSaleOrder(String id) {
        if(StringUtil.isEmpty(id)){
            return ResultBuilder.checkFailedResult("出库单id无效");
        }
        delete(id);
        saleOrderDetailService.delete(id);
        return ResultBuilder.deleteSuccessResult();
    }

    @Override
       public Result outTreasury(String id) {
        return null;
    }


    private void setSumAttr(SaleOrder saleOrder) {
        saleOrder.setTotal_amount(BigDecimal.ZERO);
        if (saleOrder.getSaleOrderDetailList() != null && saleOrder.getSaleOrderDetailList().size() > 0) {
            for (SaleOrderDetail saleOrderDetail : saleOrder.getSaleOrderDetailList()) {
                saleOrder.setTotal_amount(saleOrder.getTotal_amount().add(saleOrderDetail.getSubTotal()));
            }
        }
        if(saleOrder.getReceived_amount().compareTo(new BigDecimal(0))==0){
                saleOrder.setStatus("0");
        } else if(saleOrder.getReceived_amount().compareTo(saleOrder.getFinal_amount())<0){
                saleOrder.setStatus("1");
        }else if(saleOrder.getReceived_amount().compareTo(saleOrder.getFinal_amount())==0){
            saleOrder.setStatus("2");
        }
    }

    private List<SaleOrderDetail> convertSaleOrderDetail(List<SaleOrderDetail> saleOrderDetailList) {
        List<SaleOrderDetail> saleOrderDetails = new ArrayList<>();
        for(int i=0;i<saleOrderDetailList.size();i++){
            Map<String,Object> map = (Map<String, Object>) saleOrderDetailList.get(i);
            SaleOrderDetail saleOrderDetail = saleOrderDetailService.getBean(map);
            saleOrderDetails.add(saleOrderDetail);
        }
        return saleOrderDetails;
    }


    @Override
    @Transactional
    public Result saveSaleOrder(SaleOrder saleOrder){
        saleOrder.setId(IdGenerateUtil.getInstance().nextId().toString());
        saleOrder.setInvStatus(SysConstants.INVOUT_STATUS.INIT);
        super.save(saleOrder);
        for (SaleOrderDetail saleOrderDetail : saleOrder.getSaleOrderDetailList()) {
            saleOrderDetail.setId(IdGenerateUtil.getInstance().nextId().toString());
            saleOrderDetail.setSaleOrderid(saleOrder.getId());
            saleOrderDetailService.save(saleOrderDetail);
        }
        return Result.EMPTY_SUCC_RESULT;
    }

    @Override
    @Transactional
    public Result updateSaleOrder(SaleOrder saleOrder){
        //更新住信息
        super.update(saleOrder);
        //删除原入单详情
        saleOrderDetailService.delete("saleOrderId",saleOrder.getId());
        //插入订单详情
        for (SaleOrderDetail saleOrderDetail : saleOrder.getSaleOrderDetailList()) {
            saleOrderDetail.setId(IdGenerateUtil.getInstance().nextId().toString());
            saleOrderDetail.setSaleOrderid(saleOrder.getId());
            saleOrderDetailService.save(saleOrderDetail);
        }
        return Result.EMPTY_SUCC_RESULT;
    }

    private Result checkParams(SaleOrder saleOrder, boolean isNew) {
        if(StringUtil.isEmpty(saleOrder.getCreate_time())){
            return ResultBuilder.checkFailedResult("出库日期不能为空");
        }
        List<SaleOrderDetail> saleOrderDetailList = saleOrder.getSaleOrderDetailList();
        if(CollectionUtils.isEmpty(saleOrderDetailList)){
            return ResultBuilder.checkFailedResult("您还没有添加商品");
        }
        for (SaleOrderDetail saleOrderDetail : saleOrderDetailList) {
            if (StringUtil.isEmpty(saleOrderDetail.getProductSkuCode())) {
                return  ResultBuilder.checkFailedResult("商品编码无效");
            }
            if (StringUtil.isEmpty(saleOrderDetail.getProductSkuName())) {
                return  ResultBuilder.checkFailedResult("商品名称无效");
            }
            if (!ValidateUtil.isNumeric(saleOrderDetail.getQuantity(), ValidateUtil.NUMERIC_STATUS.POSITIVE) || saleOrderDetail.getQuantity().compareTo(0)<=0) {
                return  ResultBuilder.checkFailedResult("数量无效");
            }
            if (!ValidateUtil.isNumeric(saleOrderDetail.getPrice(), ValidateUtil.NUMERIC_STATUS.POSITIVE)) {
                return ResultBuilder.checkFailedResult("单价无效");
            }
            if (!ValidateUtil.isNumeric(saleOrderDetail.getSubTotal(), ValidateUtil.NUMERIC_STATUS.POSITIVE)) {
                return ResultBuilder.checkFailedResult("合计无效");
            }
        }
        return Result.EMPTY_SUCC_RESULT;
    }
}
