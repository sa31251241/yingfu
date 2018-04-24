package com.liaojun.webadmin.stock.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 库存统计
 * @Author: yangzi
 * @Date: 2018/4/19 14:52
 */
@Table(name = "t_inv_sum")
public class InvSum {

    @Id
    private String id;

    private Integer quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
