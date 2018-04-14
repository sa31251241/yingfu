package com.liaojun.webadmin.product.service.impl;

import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.product.model.ProductSku;
import com.liaojun.webadmin.product.service.IProductSkuService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 11:59
 */
@Service
public class ProductSkuServiceImpl extends BaseServiceImpl<ProductSku> implements IProductSkuService{
    @Override
    public Class getEntityClass() {
        return ProductSku.class;
    }
}
