package com.liaojun.webadmin.customer.mapper;

import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.mapper.IBaseMapper;
import com.liaojun.webadmin.customer.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerMapper  extends IBaseMapper<Customer> {

    Customer findById(String id);

    @Override
    List<Customer> findList(@Param("productSku") Customer customer, @Param("pageRequest") PageRequest pageRequest, @Param("sortRequest") SortRequest sortRequest);

    @Override
    Integer findListCount(@Param("productSku") Customer Customer);
}
