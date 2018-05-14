package com.liaojun.webadmin.product.service;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.webadmin.product.model.Vendor;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 11:53
 */
public interface IVendorService extends BaseService<Vendor>{
    Result saveOrUpdate(Vendor vendor);

    Result vendorDelete(String id);
}
