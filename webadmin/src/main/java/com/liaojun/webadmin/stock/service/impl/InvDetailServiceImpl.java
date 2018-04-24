package com.liaojun.webadmin.stock.service.impl;

import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.stock.model.InvDetail;
import com.liaojun.webadmin.stock.service.IInvDetailService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:34
 */
@Service
public class InvDetailServiceImpl extends BaseServiceImpl<InvDetail> implements IInvDetailService {
    @Override
    public Class getEntityClass() {
        return InvDetail.class;
    }
}
