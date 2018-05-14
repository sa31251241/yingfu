package com.liaojun.webadmin.product.service.impl;

import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.product.model.ProductSkuConfig;
import com.liaojun.webadmin.product.service.IProductSkuConfigService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 12:03
 */
@Service
public class ProductSkuConfigServiceImpl extends BaseServiceImpl<ProductSkuConfig> implements IProductSkuConfigService {
    @Override
    public Class getEntityClass() {
        return ProductSkuConfig.class;
    }
}
