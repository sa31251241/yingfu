package com.liaojun.webadmin.customer.service;


import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.webadmin.customer.model.Customer;



public interface CustomerService extends BaseService<Customer> {


    Customer findById(String id);

    PageResult findList(Customer customer, String keyword, SortRequest sortRequest, PageRequest pageRequest);

    Result saveOrUpdate(Customer customer);

    //Result delete(String id);
}
