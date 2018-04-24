package com.liaojun.webadmin.product.model;

import com.liaojun.component.base.util.BeanUtil;
import org.apache.shiro.crypto.hash.Hash;

import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 物品sku库存配置
 * @Author: yangzi
 * @Date: 2018/4/13 10:59
 */
@Table(name = "t_product_sku_config")
public class ProductSkuConfig {
    @Id
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
