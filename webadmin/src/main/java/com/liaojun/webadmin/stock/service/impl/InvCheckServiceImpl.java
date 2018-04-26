package com.liaojun.webadmin.stock.service.impl;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.service.ResultBuilder;
import com.liaojun.component.base.util.IdGenerateUtil;
import com.liaojun.component.base.util.StringUtil;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.base.constants.SysConstants;
import com.liaojun.webadmin.stock.mapper.InvCheckMapper;
import com.liaojun.webadmin.stock.model.InvCheck;
import com.liaojun.webadmin.stock.model.InvCheckDetail;
import com.liaojun.webadmin.stock.service.IInvCheckDetailService;
import com.liaojun.webadmin.stock.service.IInvCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:33
 */
@Service
public class InvCheckServiceImpl extends BaseServiceImpl<InvCheck> implements IInvCheckService {
    @Override
    public Class getEntityClass() {
        return InvCheck.class;
    }

    @Autowired
    private InvCheckMapper invCheckMapper;

    @Autowired
    private IInvCheckDetailService invCheckDetailService;

    @Override
    public InvCheck findById(String id) {
        return invCheckMapper.findById(id);
    }

    @Override
    public Result saveOrUpdate(InvCheck invCheck) {
        boolean isNew = false;
        if(StringUtil.isEmpty(invCheck.getId())){
            isNew = true;
        }
        invCheck.setInvCheckDetailList(convertInvCheckDetail(invCheck.getInvCheckDetailList()));
        Result checkResult = checkParams(invCheck,isNew);
        if(!checkResult.isSuccess()){
            return checkResult;
        }
        if(isNew){
            return saveInvCheck(invCheck);
        }else{
            return updateInvCheck(invCheck);
        }
    }

    private Result updateInvCheck(InvCheck invCheck) {
        //更新主信息
        super.update(invCheck);
        //删除原入单详情
        invCheckDetailService.delete("invCheckId",invCheck.getId());
        //插入订单详情
        for (InvCheckDetail invCheckDetail : invCheck.getInvCheckDetailList()) {
            invCheckDetail.setId(IdGenerateUtil.getInstance().nextId().toString());
            invCheckDetail.setInvCheckId(invCheck.getId());
            invCheckDetailService.save(invCheckDetail);
        }
        return ResultBuilder.updateSuccessResult();
    }

    private Result saveInvCheck(InvCheck invCheck) {
        invCheck.setId(IdGenerateUtil.getInstance().nextId().toString());
        for (InvCheckDetail invCheckDetail : invCheck.getInvCheckDetailList()) {
            invCheckDetail.setInvCheckId(invCheck.getId());
            invCheckDetailService.save(invCheckDetail);
        }
        invCheck.setStatus(SysConstants.INVCHECK_STATUS.INIT);
        super.save(invCheck);
        return ResultBuilder.saveSuccessResult();
    }

    private Result checkParams(InvCheck invCheck, boolean isNew) {
        //TODO
        /*if(StringUtil.isEmpty(invCheck.getUserId())){
            return ResultBuilder.checkFailedResult("操作员不能为空");
        }*/
        if(StringUtil.isEmpty(invCheck.getDate())){
            return ResultBuilder.checkFailedResult("盘点日期不能为空");
        }
        if(CollectionUtils.isEmpty(invCheck.getInvCheckDetailList())){
            return ResultBuilder.checkFailedResult("盘点物品无效");
        }
        for (InvCheckDetail invCheckDetail : invCheck.getInvCheckDetailList()) {
            if(StringUtil.isEmpty(invCheckDetail.getProductSkuCode())){
                return ResultBuilder.checkFailedResult("物品编码不能为空");
            }
            if(StringUtil.isEmpty(invCheckDetail.getProductSkuName())){
                return ResultBuilder.checkFailedResult("物品名称不能为空");
            }
        }
        return Result.EMPTY_SUCC_RESULT;
    }

    private List<InvCheckDetail> convertInvCheckDetail(List<InvCheckDetail> invCheckDetailList) {
        List<InvCheckDetail> invInDetails = new ArrayList<>();
        for(int i=0;i<invCheckDetailList.size();i++){
            Map<String,Object> map = (Map<String, Object>) invCheckDetailList.get(i);
            InvCheckDetail invCheckDetail = invCheckDetailService.getBean(map);
            invInDetails.add(invCheckDetail);
        }
        return invInDetails;
    }

}
