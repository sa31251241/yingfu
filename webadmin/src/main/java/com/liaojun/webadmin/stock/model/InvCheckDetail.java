package com.liaojun.webadmin.stock.model;

import javax.persistence.Id;

/**
 * 盘点单详情
 * @Author: yangzi
 * @Date: 2018/4/19 15:23
 */
public class InvCheckDetail {
    @Id
    private String id;

    private String invCheckId;

    private String productSkuId;

    private String productSkuCode;

    private String productSkuName;

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
