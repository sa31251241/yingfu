package com.liaojun.webadmin.stock.service.impl;

import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.stock.model.InvCheck;
import com.liaojun.webadmin.stock.service.IInvCheckService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:33
 */
@Service
public class InvCheckServiceImpl extends BaseServiceImpl<InvCheck> implements IInvCheckService {
    @Override
    public Class getEntityClass() {
        return InvCheck.class;
    }
}
