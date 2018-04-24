package com.liaojun.webadmin.product.service.impl;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.service.ResultBuilder;
import com.liaojun.component.base.util.StringUtil;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.product.model.Vendor;
import com.liaojun.webadmin.product.service.IVendorService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 11:55
 */
@Service
public class VendorServiceImpl extends BaseServiceImpl<Vendor> implements IVendorService{
    @Override
    public Class getEntityClass() {
        return Vendor.class;
    }

    @Override
    public Result saveOrUpdate(Vendor vendor) {
        boolean isNew = false;
        if(StringUtil.isEmpty(vendor.getId())){
            isNew = true;
        }
        Result checkResult = checkParams(vendor,isNew);
        if(!checkResult.isSuccess()){
            return checkResult;
        }
        if(isNew){
            save(vendor);
            return ResultBuilder.saveSuccessResult();
        }else{
            update(vendor);
            return ResultBuilder.updateSuccessResult();
        }
    }

    @Override
    public Result vendorDelete(String id) {
        delete(id);
        return ResultBuilder.deleteSuccessResult();
    }

    private Result checkParams(Vendor vendor,boolean isNew) {
        if(!isNew && StringUtil.isEmpty(vendor.getId())){
            return ResultBuilder.checkFailedResult("供应商编号不能为空");
        }
        if(StringUtil.isEmpty(vendor.getName())){
            return ResultBuilder.checkFailedResult("供应商名称不能为空");
        }
        if(StringUtil.isEmpty(vendor.getContact())){
            return ResultBuilder.checkFailedResult("联系人不能为空");
        }
        if(StringUtil.isEmpty(vendor.getContactMobile())){
            return ResultBuilder.checkFailedResult("联系电话不能为空");
        }
        return Result.EMPTY_SUCC_RESULT;
    }
}
