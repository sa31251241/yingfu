package com.liaojun.webadmin.stock.service.impl;

import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.stock.model.InvSum;
import com.liaojun.webadmin.stock.service.IInvSumService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:37
 */
@Service
public class InvSumServiceImpl extends BaseServiceImpl<InvSum> implements IInvSumService {
    @Override
    public Class getEntityClass() {
        return InvSum.class;
    }
}
