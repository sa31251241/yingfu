package com.liaojun.webadmin.sale.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * Created by Wuzi on 2018/5/9.
 */
@Table(name="t_sale_order_detail")
public class SaleOrderDetail {
    @Id
    private String id;


    @Column(name="sale_order_id")
    private String SaleOrderid;

    private String productSkuCode;

    private String ProductSkuName;

    private String brand;

    private String model;

    private  String productTypeName;

    private BigDecimal price;

    private Integer  quantity;

    private  BigDecimal subTotal;
    @Transient
    private  String stocks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaleOrderid() {
        return SaleOrderid;
    }

    public void setSaleOrderid(String saleOrderid) {
        SaleOrderid = saleOrderid;
    }

    public String getProductSkuCode() {
        return productSkuCode;
    }

    public void setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode;
    }

    public String getProductSkuName() {
        return ProductSkuName;
    }

    public void setProductSkuName(String ProductSkuName) {
        this.ProductSkuName = ProductSkuName;
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

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }


    public String getStocks() {
        return stocks;
    }

    public void setStocks(String stocks) {
        this.stocks = stocks;
    }
}
