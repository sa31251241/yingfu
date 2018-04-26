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
import com.liaojun.webadmin.stock.mapper.InvOutMapper;
import com.liaojun.webadmin.stock.model.*;
import com.liaojun.webadmin.stock.service.IInvDetailService;
import com.liaojun.webadmin.stock.service.IInvOutDetailService;
import com.liaojun.webadmin.stock.service.IInvOutService;
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
public class InvOutServiceImpl extends BaseServiceImpl<InvOut> implements IInvOutService {
    @Override
    public Class getEntityClass() {
        return InvOut.class;
    }

    @Autowired
    private InvOutMapper invOutMapper;

    @Autowired
    private IInvOutDetailService invOutDetailService;

    @Autowired
    private IInvSumService invSumService;

    @Autowired
    private IInvDetailService invDetailService;

    @Override
    public InvOut findById(String id) {
        return invOutMapper.findById(id);
    }

    @Override
    public Result saveOrUpdate(InvOut invOut) {
        boolean isNew = true;
        if(!StringUtil.isEmpty(invOut.getId())){
            isNew = false;
        }
        invOut.setInvOutDetailList(convertInvInDetail(invOut.getInvOutDetailList()));
        Result checkResult = checkParams(invOut,isNew);
        if(!checkResult.isSuccess()){
            return checkResult;
        }
        setSumAttr(invOut);
        if(isNew){
            saveInvOut(invOut);
            return ResultBuilder.saveSuccessResult();
        }else{
            updateInvOut(invOut);
            return ResultBuilder.saveSuccessResult();
        }
    }

    @Override
    @Transactional
    public Result deleteInvOut(String id) {
        if(StringUtil.isEmpty(id)){
            return ResultBuilder.checkFailedResult("出库单id无效");
        }
        delete(id);
        invOutDetailService.delete("invOutId",id);
        return ResultBuilder.deleteSuccessResult();
    }

    @Override
    @Transactional
    public Result outTreasury(String id) {
        InvOut invOut = findById(id);
        if(invOut == null){
            return ResultBuilder.checkFailedResult("未查询到出库单");
        }
        for (InvOutDetail invOutDetail : invOut.getInvOutDetailList()) {
            if(invOutDetail.getQuantity() > invOutDetail.getStocks()){
                return ResultBuilder.checkFailedResult("商品编码："+invOutDetail.getProductSkuCode()+"库存不足，无法出库");
            }
        }
        if(!SysConstants.INVOUT_STATUS.INIT.equals(invOut.getStatus())){
            return ResultBuilder.checkFailedResult("出库单状态有误");
        }
        invOut(invOut);
        super.update(id,"status",SysConstants.INVOUT_STATUS.FINISH);
        return Result.EMPTY_SUCC_RESULT.setMessage("出库成功");
    }

    /**
     * 出库操作
     * @param invOut
     * @return
     */
    private Result invOut(InvOut invOut) {
        for (InvOutDetail invOutDetail : invOut.getInvOutDetailList()) {
            updateInvDetail(invOutDetail);
            updateInvSum(invOutDetail);
        }
        return Result.EMPTY_SUCC_RESULT;
    }

    private void updateInvDetail(InvOutDetail invOutDetail) {
        try {
            InvDetail invDetail = new InvDetail();
            BeanUtil.copyProperties(invDetail,invOutDetail);
            invDetail.setId(IdGenerateUtil.getInstance().nextId().toString());
            invDetail.setInvOrderType(SysConstants.INV_IN_OR_OUT.OUT);
            invDetailService.save(invDetail);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("出库异常");
        }
    }

    private void updateInvSum(InvOutDetail invOutDetail) {
        InvSum invSum = invSumService.get(ComponentMyBatisConstant.DB_KEY.ID.getValue(),invOutDetail.getProductSkuCode());
        if(invSum == null){
            invSum = new InvSum();
            invSum.setId(invOutDetail.getProductSkuCode());
            invSum.setQuantity(invOutDetail.getQuantity());
            invSumService.save(invSum);
        }else{
            invSumService.update(invSum.getId(),"quantity",invSum.getQuantity()-invOutDetail.getQuantity());
        }
    }


    private void setSumAttr(InvOut invOut) {
        invOut.setTotalPrice(BigDecimal.ZERO);
        if (invOut.getInvOutDetailList() != null && invOut.getInvOutDetailList().size() > 0) {
            for (InvOutDetail invOutDetail : invOut.getInvOutDetailList()) {
                invOut.setTotalPrice(invOut.getTotalPrice().add(invOutDetail.getSubTotal()));
            }
        }
    }

    private List<InvOutDetail> convertInvInDetail(List<InvOutDetail> invOutDetailList) {
        List<InvOutDetail> invOutDetails = new ArrayList<>();
        for(int i=0;i<invOutDetailList.size();i++){
            Map<String,Object> map = (Map<String, Object>) invOutDetailList.get(i);
            InvOutDetail invOutDetail = invOutDetailService.getBean(map);
            invOutDetails.add(invOutDetail);
        }
        return invOutDetails;
    }

    @Override
    @Transactional
    public Result saveInvOut(InvOut invOut){
        invOut.setId(IdGenerateUtil.getInstance().nextId().toString());
        invOut.setStatus(SysConstants.INVOUT_STATUS.INIT);
        super.save(invOut);
        for (InvOutDetail invOutDetail : invOut.getInvOutDetailList()) {
            invOutDetail.setId(IdGenerateUtil.getInstance().nextId().toString());
            invOutDetail.setInvOutId(invOut.getId());
            invOutDetailService.save(invOutDetail);
        }
        return Result.EMPTY_SUCC_RESULT;
    }

    @Override
    @Transactional
    public Result updateInvOut(InvOut invOut){
        //更新住信息
        super.update(invOut);
        //删除原入单详情
        invOutDetailService.delete("invOutId",invOut.getId());
        //插入订单详情
        for (InvOutDetail invOutDetail : invOut.getInvOutDetailList()) {
            invOutDetail.setId(IdGenerateUtil.getInstance().nextId().toString());
            invOutDetail.setInvOutId(invOut.getId());
            invOutDetailService.save(invOutDetail);
        }
        return Result.EMPTY_SUCC_RESULT;
    }


    private Result checkParams(InvOut invOut, boolean isNew) {
        if(StringUtil.isEmpty(invOut.getType())){
            return ResultBuilder.checkFailedResult("出库类型不能为空");
        }
        if(SysConstants.INVOUT_TYPE.TAKE.equals(invOut.getType())){
            //领用出库
            if(StringUtil.isEmpty(invOut.getTakeUserId())){
                return ResultBuilder.checkFailedResult("领用人不能为空");
            }
        }
        if(StringUtil.isEmpty(invOut.getDate())){
            return ResultBuilder.checkFailedResult("出库日期不能为空");
        }
        List<InvOutDetail> invOutDetailList = invOut.getInvOutDetailList();
        if(CollectionUtils.isEmpty(invOutDetailList)){
            return ResultBuilder.checkFailedResult("入库商品无效");
        }
        for (InvOutDetail invOutDetail : invOutDetailList) {
            if (StringUtil.isEmpty(invOutDetail.getProductSkuCode())) {
                return  ResultBuilder.checkFailedResult("商品编码无效");
            }
            if (StringUtil.isEmpty(invOutDetail.getProductSkuName())) {
                return  ResultBuilder.checkFailedResult("商品名称无效");
            }
            if (!ValidateUtil.isNumeric(invOutDetail.getQuantity(), ValidateUtil.NUMERIC_STATUS.POSITIVE) || invOutDetail.getQuantity().compareTo(0)<=0) {
                return  ResultBuilder.checkFailedResult("数量无效");
            }
            if (!ValidateUtil.isNumeric(invOutDetail.getUnitPrice(), ValidateUtil.NUMERIC_STATUS.POSITIVE)) {
                return ResultBuilder.checkFailedResult("单价无效");
            }
            if (!ValidateUtil.isNumeric(invOutDetail.getSubTotal(), ValidateUtil.NUMERIC_STATUS.POSITIVE)) {
                return ResultBuilder.checkFailedResult("合计无效");
            }
        }
        return Result.EMPTY_SUCC_RESULT;
    }
}
