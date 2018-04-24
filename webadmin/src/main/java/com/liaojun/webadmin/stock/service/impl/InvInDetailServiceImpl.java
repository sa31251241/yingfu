package com.liaojun.webadmin.stock.service.impl;

import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.stock.model.InvInDetail;
import com.liaojun.webadmin.stock.service.IInvInDetailService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:35
 */
@Service
public class InvInDetailServiceImpl extends BaseServiceImpl<InvInDetail> implements IInvInDetailService {
    @Override
    public Class getEntityClass() {
        return InvInDetail.class;
    }
}
