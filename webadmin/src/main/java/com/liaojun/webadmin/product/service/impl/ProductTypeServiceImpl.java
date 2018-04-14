package com.liaojun.webadmin.product.service.impl;

import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.product.model.ProductType;
import com.liaojun.webadmin.product.service.IProductTypeService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 12:01
 */
@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType> implements IProductTypeService {
    @Override
    public Class getEntityClass() {
        return ProductType.class;
    }
}
