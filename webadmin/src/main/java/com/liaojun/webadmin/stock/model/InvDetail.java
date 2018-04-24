package com.liaojun.webadmin.stock.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 库存明细
 * @Author: yangzi
 * @Date: 2018/4/19 14:55
 */
@Table(name = "t_inv_detail")
public class InvDetail  {

    @Id
    private String id;

    private String productSkuId;

    private String productSkuCode;

    private String productSkuName;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subTotal;

    /**
     * 出入库类型
     */
    private Integer invOrderType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getInvOrderType() {
        return invOrderType;
    }

    public void setInvOrderType(Integer invOrderType) {
        this.invOrderType = invOrderType;
    }
}
