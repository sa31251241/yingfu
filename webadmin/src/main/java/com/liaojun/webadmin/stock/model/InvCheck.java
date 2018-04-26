package com.liaojun.webadmin.stock.model;

import com.liaojun.component.base.annotation.DictField;
import com.liaojun.component.base.db.model.BaseModel;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 盘点单
 * @Author: yangzi
 * @Date: 2018/4/19 15:21
 */
@Table(name = "t_inv_check")
public class InvCheck extends BaseModel{

    @Id
    private String id;

    private String userId;

    @Transient
    private String userName;

    private String date;

    private String remark;

    @DictField(constantKey = "INVCHECK_STATUS")
    private Integer status;

    @Transient
    private String startDate;

    @Transient
    private String endDate;

    @Transient
    private List<InvCheckDetail> invCheckDetailList;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<InvCheckDetail> getInvCheckDetailList() {
        return invCheckDetailList;
    }

    public void setInvCheckDetailList(List<InvCheckDetail> invCheckDetailList) {
        this.invCheckDetailList = invCheckDetailList;
    }
}
