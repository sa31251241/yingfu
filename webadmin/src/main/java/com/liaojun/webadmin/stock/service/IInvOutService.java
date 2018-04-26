package com.liaojun.webadmin.stock.service;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.webadmin.stock.model.InvIn;
import com.liaojun.webadmin.stock.model.InvOut;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:36
 */
public interface IInvOutService extends BaseService<InvOut> {

    InvOut findById(String id);

    Result saveOrUpdate(InvOut invOut);

    Result saveInvOut(InvOut invOut);

    Result updateInvOut(InvOut invOut);

    Result deleteInvOut(String id);

    Result outTreasury(String id);
}
