package com.liaojun.webadmin.sale.service;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.webadmin.sale.model.SaleOrder;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:36
 */
public interface SaleOrderService extends BaseService<SaleOrder> {

    SaleOrder findById(String id);

    Result saveOrUpdate(SaleOrder saleOrder);

    Result saveSaleOrder(SaleOrder saleOrder);

    Result updateSaleOrder(SaleOrder saleOrder);

    Result deleteSaleOrder(String id);

    Result outTreasury(String id);
}
