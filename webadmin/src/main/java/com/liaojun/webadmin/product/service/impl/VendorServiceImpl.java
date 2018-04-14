package com.liaojun.webadmin.product.service.impl;

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
}
