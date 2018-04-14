package com.liaojun.webadmin.product.model;

/**
 * 商品sku库存配置
 * @Author: yangzi
 * @Date: 2018/4/13 10:59
 */
public class ProductSkuConfig {
    private String id;

    private String productSkuId;

    private Integer maxStorage;

    private Integer minStorage;

    private Integer expireWarning;

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

    public Integer getMaxStorage() {
        return maxStorage;
    }

    public void setMaxStorage(Integer maxStorage) {
        this.maxStorage = maxStorage;
    }

    public Integer getMinStorage() {
        return minStorage;
    }

    public void setMinStorage(Integer minStorage) {
        this.minStorage = minStorage;
    }

    public Integer getExpireWarning() {
        return expireWarning;
    }

    public void setExpireWarning(Integer expireWarning) {
        this.expireWarning = expireWarning;
    }
}
