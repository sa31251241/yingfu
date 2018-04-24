package com.liaojun.webadmin.stock.model;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 出库单详情
 * @Author: yangzi
 * @Date: 2018/4/19 14:49
 */
public class InvOutDetail {

    @Id
    private String id;

    private String invOutId;

    private String productSkuId;

    private String productSkuCode;

    private String productSkuName;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subtotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvOutId() {
        return invOutId;
    }

    public void setInvOutId(String invOutId) {
        this.invOutId = invOutId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
