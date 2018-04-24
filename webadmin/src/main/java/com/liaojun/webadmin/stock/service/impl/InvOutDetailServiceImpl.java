package com.liaojun.webadmin.stock.service.impl;

import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.stock.model.InvOutDetail;
import com.liaojun.webadmin.stock.service.IInvOutDetailService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:37
 */
@Service
public class InvOutDetailServiceImpl extends BaseServiceImpl<InvOutDetail> implements IInvOutDetailService {
    @Override
    public Class getEntityClass() {
        return InvOutDetail.class;
    }
}
