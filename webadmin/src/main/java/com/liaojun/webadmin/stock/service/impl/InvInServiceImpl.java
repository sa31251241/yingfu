package com.liaojun.webadmin.stock.service.impl;

import com.liaojun.component.base.common.model.BusinessException;
import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.service.ResultBuilder;
import com.liaojun.component.base.util.BeanUtil;
import com.liaojun.component.base.util.IdGenerateUtil;
import com.liaojun.component.base.util.StringUtil;
import com.liaojun.component.base.util.ValidateUtil;
import com.liaojun.component.mybatis.constant.ComponentMyBatisConstant;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.base.constants.SysConstants;
import com.liaojun.webadmin.stock.mapper.InvInMapper;
import com.liaojun.webadmin.stock.model.InvDetail;
import com.liaojun.webadmin.stock.model.InvIn;
import com.liaojun.webadmin.stock.model.InvInDetail;
import com.liaojun.webadmin.stock.model.InvSum;
import com.liaojun.webadmin.stock.service.IInvDetailService;
import com.liaojun.webadmin.stock.service.IInvInDetailService;
import com.liaojun.webadmin.stock.service.IInvInService;
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
 * @Date: 2018/4/19 15:35
 */
@Service
public class InvInServiceImpl extends BaseServiceImpl<InvIn> implements IInvInService {
    @Override
    public Class getEntityClass() {
        return InvIn.class;
    }

    @Autowired
    private InvInMapper invInMapper;

    @Autowired
    private IInvInDetailService invInDetailService;

    @Autowired
    private IInvDetailService invDetailService;

    @Autowired
    private IInvSumService invSumService;


    @Override
    public InvIn findById(String id) {
        return invInMapper.findById(id);
    }

    @Override
    @Transactional
    public Result saveOrUpdate(InvIn invIn) {
        boolean isNew = true;
        if(!StringUtil.isEmpty(invIn.getId())){
            isNew = false;
        }
        invIn.setInvInDetailList(convertInvInDetail(invIn.getInvInDetailList()));
        Result checkResult = checkParams(invIn,isNew);
        if(!checkResult.isSuccess()){
            return checkResult;
        }
        setSumAttr(invIn);
        if(isNew){
            return saveInvIn(invIn);
        }else{
            return updateInvIn(invIn);
        }
    }

    @Override
    @Transactional
    public Result deleteInvIn(String id) {
        if(StringUtil.isEmpty(id)){
           return ResultBuilder.checkFailedResult("入库单id无效");
        }
        delete(id);
        invInDetailService.delete("invInId",id);
        return ResultBuilder.deleteSuccessResult();
    }

    @Override
    @Transactional
    public Result wareHousing(String id) {
        InvIn invIn = findById(id);
        if(invIn == null){
            return ResultBuilder.checkFailedResult("入库单id无效");
        }
        if(!SysConstants.INVIN_STATUS.INIT.equals(invIn.getStatus())){
            return ResultBuilder.checkFailedResult("入库单状态有误");
        }
        invIn(invIn);
        super.update(id,"status",SysConstants.INVIN_STATUS.FINISH);
        return Result.EMPTY_SUCC_RESULT.setMessage("入库成功");
    }


    private Result invIn(InvIn invIn) {
        for (InvInDetail invInDetail : invIn.getInvInDetailList()) {
            updateInvDetail(invInDetail);
            updateInvSum(invInDetail);
        }
        return Result.EMPTY_SUCC_RESULT;
    }

    private void updateInvDetail(InvInDetail invInDetail) {
        try {
            InvDetail invDetail = new InvDetail();
            BeanUtil.copyProperties(invDetail,invInDetail);
            invDetail.setId(IdGenerateUtil.getInstance().nextId().toString());
            invDetail.setInvOrderType(SysConstants.INV_IN_OR_OUT.IN);
            invDetailService.save(invDetail);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("入库异常");
        }
    }

    private void updateInvSum(InvInDetail invInDetail) {
        InvSum invSum = invSumService.get(ComponentMyBatisConstant.DB_KEY.ID.getValue(),invInDetail.getProductSkuCode());
        if(invSum == null){
            invSum = new InvSum();
            invSum.setId(invInDetail.getProductSkuCode());
            invSum.setQuantity(invInDetail.getQuantity());
            invSumService.save(invSum);
        }else{
            invSumService.update(invSum.getId(),"quantity",invSum.getQuantity()+invInDetail.getQuantity());
        }
    }

    private void setSumAttr(InvIn invIn) {
        invIn.setQuantity(0);
        invIn.setTotalPrice(BigDecimal.ZERO);
        if (invIn.getInvInDetailList() != null && invIn.getInvInDetailList().size() > 0) {
            for (InvInDetail invInDetail : invIn.getInvInDetailList()) {
                invIn.setQuantity(invIn.getQuantity()+invInDetail.getQuantity());
                invIn.setTotalPrice(invIn.getTotalPrice().add(invInDetail.getSubTotal()));
            }
        }
    }

    private List<InvInDetail> convertInvInDetail(List<InvInDetail> invInDetailList) {
        List<InvInDetail> invInDetails = new ArrayList<>();
        for(int i=0;i<invInDetailList.size();i++){
            Map<String,Object> map = (Map<String, Object>) invInDetailList.get(i);
            InvInDetail invInDetail = invInDetailService.getBean(map);
            invInDetails.add(invInDetail);
        }
        return invInDetails;
    }

    private Result saveInvIn(InvIn invIn){
        invIn.setId(IdGenerateUtil.getInstance().nextId().toString());
        invIn.setStatus(SysConstants.INVIN_STATUS.INIT);
        super.save(invIn);
        for (InvInDetail invInDetail : invIn.getInvInDetailList()) {
            invInDetail.setId(IdGenerateUtil.getInstance().nextId().toString());
            invInDetail.setInvInId(invIn.getId());
            invInDetailService.save(invInDetail);
        }
        return ResultBuilder.saveSuccessResult();
    }

    private Result updateInvIn(InvIn invIn){
        //更新住信息
        super.update(invIn);
        //删除原入单详情
        invInDetailService.delete("invInId",invIn.getId());
        //插入订单详情
        for (InvInDetail invInDetail : invIn.getInvInDetailList()) {
            invInDetail.setId(IdGenerateUtil.getInstance().nextId().toString());
            invInDetail.setInvInId(invIn.getId());
            invInDetailService.save(invInDetail);
        }
        return ResultBuilder.updateSuccessResult();
    }


    private Result checkParams(InvIn invIn, boolean isNew) {
        if(StringUtil.isEmpty(invIn.getVendorId())){
            return ResultBuilder.checkFailedResult("必须选择供应商");
        }
        if(StringUtil.isEmpty(invIn.getType())){
            return ResultBuilder.checkFailedResult("入库类型不能为空");
        }
        if(StringUtil.isEmpty(invIn.getDate())){
            return ResultBuilder.checkFailedResult("入库日期不能为空");
        }
        List<InvInDetail> invInDetailList = invIn.getInvInDetailList();
        if(CollectionUtils.isEmpty(invInDetailList)){
            return ResultBuilder.checkFailedResult("入库商品无效");
        }
        for (InvInDetail invInDetail : invInDetailList) {
            if (StringUtil.isEmpty(invInDetail.getProductSkuCode())) {
                return  ResultBuilder.checkFailedResult("商品编码无效");
            }
            if (StringUtil.isEmpty(invInDetail.getProductSkuName())) {
                return  ResultBuilder.checkFailedResult("商品名称无效");
            }
            if (!ValidateUtil.isNumeric(invInDetail.getQuantity(), ValidateUtil.NUMERIC_STATUS.POSITIVE) || invInDetail.getQuantity().compareTo(0)<=0) {
                return  ResultBuilder.checkFailedResult("数量无效");
            }
            if (!ValidateUtil.isNumeric(invInDetail.getUnitPrice(), ValidateUtil.NUMERIC_STATUS.POSITIVE)) {
                return ResultBuilder.checkFailedResult("单价无效");
            }
            if (!ValidateUtil.isNumeric(invInDetail.getSubTotal(), ValidateUtil.NUMERIC_STATUS.POSITIVE)) {
                return ResultBuilder.checkFailedResult("合计无效");
            }
        }
        return Result.EMPTY_SUCC_RESULT;
    }
}
