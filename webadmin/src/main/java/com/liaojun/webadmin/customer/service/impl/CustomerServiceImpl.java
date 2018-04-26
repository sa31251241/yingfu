package com.liaojun.webadmin.customer.service.impl;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.base.service.ResultBuilder;
import com.liaojun.component.base.util.StringUtil;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.customer.mapper.CustomerMapper;
import com.liaojun.webadmin.customer.model.Customer;
import com.liaojun.webadmin.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {
    @Override
    public Class getEntityClass() {
        return Customer.class;
    }

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer findById(String id) {
        return customerMapper.findById(id);
    }

    @Override
    public PageResult findList(Customer customer, String keyword, SortRequest sortRequest, PageRequest pageRequest) {
        if (!StringUtil.isEmpty(keyword)) {
            customer.setCusName(keyword);
        }
        return super.findList(customer, pageRequest, sortRequest);
    }

    @Override
    public Result saveOrUpdate(Customer customer) {
        boolean isNew = false;
        if (StringUtil.isEmpty(customer.getId())) {
            isNew = true;
        }
        Result checkresult = checkParams(customer, isNew);
        if (!checkresult.isSuccess()) {
            return checkresult;
        }
        if (isNew) {
            save(customer);
            return ResultBuilder.saveSuccessResult();
        } else {
            update(customer);
            return ResultBuilder.updateSuccessResult();
        }
    }

    private Result checkParams(Customer customer, boolean isNew) {
        if (exist("id", customer.getId())) {
            if (isNew) {
                return ResultBuilder.checkFailedResult("该客户已存在");
            }
        }
        if (StringUtil.isEmpty(customer.getCusName())) {
            return ResultBuilder.checkFailedResult("客户姓名必须输入");
        }
        if (StringUtil.isEmpty(customer.getCusSex())) {
            return ResultBuilder.checkFailedResult("客户性别必须输入");
        }
        if (StringUtil.isEmpty(customer.getCusPhone())) {
            return ResultBuilder.checkFailedResult("客户联系方式必须输入");
        }
        if (StringUtil.isEmpty(customer.getCusAddress())) {
            return ResultBuilder.checkFailedResult("客户地址必须输入");
        }
        return Result.EMPTY_SUCC_RESULT;
    }

}
