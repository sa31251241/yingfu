package com.liaojun.webadmin.stock.model;

import com.liaojun.component.base.db.model.BaseModel;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 出库单
 * @Author: yangzi
 * @Date: 2018/4/19 14:46
 */
public class InvOut extends BaseModel{

    @Id
    private String id;

    private Integer type;

    private String date;

    private String takeUserId;

    private String takeUserName;

    private BigDecimal totalPrice;

    private String remark;

    private Integer status;

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
}
