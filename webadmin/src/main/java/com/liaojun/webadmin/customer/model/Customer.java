package com.liaojun.webadmin.customer.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 客户实体
 */
@Table(name = "t_customer")
public class Customer {
    //客户id
    @Id
    @Column(name = "cus_id")
    private String id;
    //客户姓名
    private String cusName;
    //客户性别
    private String cusSex;
    //客户联系方式
    private String cusPhone;
    //  客户地址
    private String cusAddress;
    //  添加时间
    private String addTime;
    //备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusSex() {
        return cusSex;
    }

    public void setCusSex(String cusSex) {
        this.cusSex = cusSex;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Customer() {
    }
}
