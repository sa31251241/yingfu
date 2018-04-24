package com.liaojun.webadmin.base.controller;

import com.alibaba.fastjson.JSON;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.webadmin.base.model.LayuiPageResult;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/16 1:30
 */
@Controller
public class BaseController {

    protected  String printPageResult(PageResult pageResult){
        LayuiPageResult layuiPageResult = new LayuiPageResult(pageResult.getRecordCount(),pageResult.getRecords());
        return JSON.toJSONString(layuiPageResult);
    }

    protected  String printListResult(List list){
        LayuiPageResult layuiPageResult = new LayuiPageResult(list.size(),list);
        return JSON.toJSONString(layuiPageResult);
    }
}
