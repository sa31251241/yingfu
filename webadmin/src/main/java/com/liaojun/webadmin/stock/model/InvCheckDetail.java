package com.liaojun.webadmin.stock.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 盘点单详情
 * @Author: yangzi
 * @Date: 2018/4/19 15:23
 */
@Table(name = "t_inv_check_detail")
public class InvCheckDetail {
    @Id
    private String id;

    private String invCheckId;

    private String productSkuId;

    private String productSkuCode;

    private String productSkuName;

    private String productTypeName;

    private String brand;

    private String model;

    private String vendorName;

    private Integer stocks;

    /**
     * 盘点数量
     */
    private Integer checkQuantity;

    /**
     * 差异数量
     */
    private Integer diffQuantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvCheckId() {
        return invCheckId;
    }

    public void setInvCheckId(String invCheckId) {
        this.invCheckId = invCheckId;
    }

    public String getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(String productSkuId) {
        this.productSkuId = productSkuId;
    }

    public String getProductSkuCode() {
        return productSkuCode;
    }

    public void setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode;
    }

    public String getProductSkuName() {
        return productSkuName;
    }

    public void setProductSkuName(String productSkuName) {
        this.productSkuName = productSkuName;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public Integer getCheckQuantity() {
        return checkQuantity;
    }

    public void setCheckQuantity(Integer checkQuantity) {
        this.checkQuantity = checkQuantity;
    }

    public Integer getDiffQuantity() {
        return diffQuantity;
    }

    public void setDiffQuantity(Integer diffQuantity) {
        this.diffQuantity = diffQuantity;
    }

}
