package com.liaojun.webadmin.sale.model;

import com.liaojun.component.base.annotation.DictField;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Wuzi on 2018/5/9.
 */
@Table(name = "t_sale_order")
public class SaleOrder {
    @Id
    private String id;
    private String customer_id;
    @Transient
    private String customer_name;
    @Transient
    private String cusPhone;
    /**
     * 欠费
     */
    @Transient
    private String  arrearage;
    private BigDecimal total_amount;
    private BigDecimal discount;
    private BigDecimal free_amount;
    private BigDecimal final_amount;
    private BigDecimal received_amount;
    @DictField(constantKey = "INVOUT_STATUS")
    private Integer invStatus;
    @DictField(constantKey = "SALEORDER_STATUS")
    private String status;
    private String create_time;
    private String create_user;

    private String update_user;
    private String update_time;
    private String remark;
    @Transient
    private String address;
    @Transient
    private String startDate;
    @Transient
    private String endDate;

    @Transient
    private List<SaleOrderDetail> saleOrderDetailList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getFree_amount() {
        return free_amount;
    }

    public void setFree_amount(BigDecimal free_amount) {
        this.free_amount = free_amount;
    }

    public BigDecimal getFinal_amount() {
        return final_amount;
    }

    public void setFinal_amount(BigDecimal final_amount) {
        this.final_amount = final_amount;
    }

    public BigDecimal getReceived_amount() {
        return received_amount;
    }

    public void setReceived_amount(BigDecimal received_amount) {
        this.received_amount = received_amount;
    }


    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
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


    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public List<SaleOrderDetail> getSaleOrderDetailList() {
        return saleOrderDetailList;
    }

    public void setSaleOrderDetailList(List<SaleOrderDetail> saleOrderDetailList) {
        this.saleOrderDetailList = saleOrderDetailList;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getInvStatus() {
        return invStatus;
    }

    public void setInvStatus(Integer invStatus) {
        this.invStatus = invStatus;
    }

    public String getcusPhone() {
        return cusPhone;
    }

    public void setcusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getArrearage() {
        return arrearage;
    }

    public void setArrearage(String arrearage) {
        this.arrearage = arrearage;
    }
}
