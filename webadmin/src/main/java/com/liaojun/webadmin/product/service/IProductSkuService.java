package com.liaojun.webadmin.product.service;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.webadmin.product.model.ProductSku;
import com.liaojun.webadmin.product.model.ProductSkuConfig;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 11:57
 */
public interface IProductSkuService extends BaseService<ProductSku>{
    ProductSku findById(String id);

    PageResult findList(ProductSku populate, String keyword, SortRequest sortRequest, PageRequest pageRequest);

    Result saveOrUpdate(ProductSku productSku,ProductSkuConfig productSkuConfig);

    Result deleteProductSku(String id);

    PageResult findWarningList(ProductSku productSku,String keyword, PageRequest pageRequest);
}
