package com.liaojun.webadmin.stock.service;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.webadmin.stock.model.InvIn;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:35
 */
public interface IInvInService extends BaseService<InvIn> {
    PageResult findList(InvIn invIn, PageRequest pageRequest, SortRequest sortRequest);

    InvIn findById(String id);

    Result saveOrUpdate(InvIn invIn);

    Result saveInvIn(InvIn invIn);

    Result updateInvIn(InvIn invIn);

    Result deleteInvIn(String id);

    Result wareHousing(String id);
}
