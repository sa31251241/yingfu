package com.liaojun.webadmin.product.model;

import com.liaojun.component.base.db.model.BaseModel;

/**
 * 商品类别
 * @Author: yangzi
 * @Date: 2018/4/13 10:07
 */
public class ProductType extends BaseModel{
    private String pid;
    private String name;
    private String remark;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
