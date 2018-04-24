package com.liaojun.webadmin.base.model;

import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/16 1:27
 */
public class LayuiPageResult {

    private Integer count;
    private List data;
    private String code = "0";
    private String msg = "";

    public LayuiPageResult(){};

    public LayuiPageResult(Integer count, List data) {
        this.count = count;
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
