package com.liaojun.webadmin.stock.service;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.webadmin.stock.model.InvCheck;
import com.liaojun.webadmin.stock.model.InvOut;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:33
 */
public interface IInvCheckService extends BaseService<InvCheck>{
    InvCheck findById(String id);

    Result saveOrUpdate(InvCheck invCheck);

}
