package com.liaojun.webadmin.stock.service.impl;

import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.stock.model.InvOut;
import com.liaojun.webadmin.stock.service.IInvOutService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:36
 */
@Service
public class InvOutServiceImpl extends BaseServiceImpl<InvOut> implements IInvOutService {
    @Override
    public Class getEntityClass() {
        return InvOut.class;
    }
}
