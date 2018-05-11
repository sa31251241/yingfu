package com.liaojun.webadmin.customer.mapper;

import com.liaojun.component.mybatis.mapper.IBaseMapper;
import com.liaojun.webadmin.customer.model.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper  extends IBaseMapper<Customer> {
}
