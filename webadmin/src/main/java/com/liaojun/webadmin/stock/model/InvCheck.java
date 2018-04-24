package com.liaojun.webadmin.stock.model;

import com.liaojun.component.base.db.model.BaseModel;

import javax.persistence.Id;

/**
 * 盘点单
 * @Author: yangzi
 * @Date: 2018/4/19 15:21
 */
public class InvCheck extends BaseModel{

    @Id
    private String id;

    private String userId;

    private String userName;

    private String data;

    private String remark;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
