package com.liaojun.webadmin.stock.service.impl;

import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.stock.model.InvCheckDetail;
import com.liaojun.webadmin.stock.service.IInvCheckDetailService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:34
 */
@Service
public class InvCheckDetailServiceImpl extends BaseServiceImpl<InvCheckDetail> implements IInvCheckDetailService {
    @Override
    public Class getEntityClass() {
        return InvCheckDetail.class;
    }
}
