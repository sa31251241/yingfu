package com.liaojun.webadmin.product.service;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.webadmin.product.model.ProductType;
import com.liaojun.webadmin.web.product.model.ProductTypeTree;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 11:58
 */
public interface IProductTypeService extends BaseService<ProductType>{
    ProductTypeTree getTree();

    Result saveOrUpdate(ProductType productType);

    Result deleteProductType(String id);

    ProductType findById(String id);
}
