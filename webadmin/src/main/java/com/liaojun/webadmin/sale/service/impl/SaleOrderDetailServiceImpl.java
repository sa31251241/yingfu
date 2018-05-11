package com.liaojun.webadmin.sale.service.impl;

import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.sale.model.SaleOrderDetail;
import com.liaojun.webadmin.sale.service.SaleOrderDetailService;
import com.liaojun.webadmin.stock.model.InvOutDetail;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:37
 */
@Service
public class SaleOrderDetailServiceImpl extends BaseServiceImpl<SaleOrderDetail> implements SaleOrderDetailService {
    @Override
    public Class getEntityClass() {
        return SaleOrderDetail.class;
    }
}
