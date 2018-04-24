package com.liaojun.webadmin.stock.model;

import com.liaojun.component.base.annotation.DictField;
import com.liaojun.component.base.db.model.BaseModel;
import com.liaojun.component.base.util.BeanUtil;
import com.liaojun.webadmin.system.model.User;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 入库单
 * @Author: yangzi
 * @Date: 2018/4/19 14:38
 */
@Table(name = "t_invIn")
public class InvIn extends BaseModel{

    @Id
    private String id;

    private String vendorId;

    @Transient
    private String vendorName;

    @DictField(constantKey = "INVIN_TYPE")
    private Integer type;

    private String date;

    private Integer quantity;

    private BigDecimal totalPrice;

    private String remark;

    @DictField(constantKey = "INVIN_STATUS")
    private Integer status;

    @Transient
    private String startDate;

    @Transient
    private String endDate;

    @Transient
    private List<InvInDetail> invInDetailList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public List<InvInDetail> getInvInDetailList() {
        return invInDetailList;
    }

    public void setInvInDetailList(List<InvInDetail> invInDetailList) {
        this.invInDetailList = invInDetailList;
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


    public static void main(String[] args) {
        InvIn invIn = new InvIn();
        List<User> invInDetailList = new ArrayList<>();
        User user = new User();
        user.setRoleId("sfasf");
        user.setRoleName("safafas");
        invInDetailList.add(user);
        try {
            BeanUtil.setProperty(invIn,"invInDetailList",invInDetailList);
            System.out.println(invIn);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
