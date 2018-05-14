package com.liaojun.webadmin.product.model;

import com.liaojun.component.base.db.model.BaseModel;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 供应商
 * @Author: yangzi
 * @Date: 2018/4/13 11:50
 */
@Table(name = "t_vendor")
public class Vendor extends BaseModel{

    @Id
    private String id;

    private String name;

    private String address;

    private String telphone;

    private String contact;

    private String contactMobile;

    private String email;

    private String fax;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
