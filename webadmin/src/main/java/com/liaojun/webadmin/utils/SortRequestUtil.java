package com.liaojun.webadmin.utils;

import com.liaojun.component.base.constant.ComponentBaseConstant;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.constant.ComponentMyBatisConstant;

/**
 * @Author: yangzi
 * @Date: 2018/4/18 23:35
 */
public class SortRequestUtil {

    public static SortRequest buildCreateTimeAscSort(){
        return new SortRequest(ComponentMyBatisConstant.BASE_DB_ATTR.CREATE_TIME.getValue(), ComponentBaseConstant.SORT_REQUEST_DIRECT.ASC.getValue());
    }

    public static SortRequest buildCreateTimeDescSort(){
        return new SortRequest(ComponentMyBatisConstant.BASE_DB_ATTR.CREATE_TIME.getValue(), ComponentBaseConstant.SORT_REQUEST_DIRECT.DESC.getValue());
    }
}
