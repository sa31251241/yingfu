package com.liaojun.webadmin.stock.model;

import com.liaojun.component.base.annotation.DictField;
import com.liaojun.component.base.db.model.BaseModel;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

/**
 * 出库单
 * @Author: yangzi
 * @Date: 2018/4/19 14:46
 */
@Table(name = "t_invOut")
public class InvOut extends BaseModel{

    @Id
    private String id;

    @DictField(constantKey = "INVOUT_TYPE")
    private Integer type;

    private String date;

    private String takeUserId;

    @Transient
    private String takeUserName;

    private BigDecimal totalPrice;

    private String remark;

    @DictField(constantKey = "INVOUT_STATUS")
    private Integer status;

    @Transient
    private String startDate;

    @Transient
    private String endDate;

    @Transient
    private List<InvOutDetail> invOutDetailList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTakeUserId() {
        return takeUserId;
    }

    public void setTakeUserId(String takeUserId) {
        this.takeUserId = takeUserId;
    }

    public String getTakeUserName() {
        return takeUserName;
    }

    public void setTakeUserName(String takeUserName) {
        this.takeUserName = takeUserName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public List<InvOutDetail> getInvOutDetailList() {
        return invOutDetailList;
    }

    public void setInvOutDetailList(List<InvOutDetail> invOutDetailList) {
        this.invOutDetailList = invOutDetailList;
    }
}
