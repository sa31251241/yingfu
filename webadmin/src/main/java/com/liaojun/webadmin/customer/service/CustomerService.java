package com.liaojun.webadmin.customer.service;


import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.webadmin.customer.model.Customer;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface CustomerService extends BaseService<Customer> {


    Result saveOrUpdate(Customer customer);

    Result customerDelete(String id);

    void customerExport(List data, HttpServletResponse response);
}
