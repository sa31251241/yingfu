package com.liaojun.webadmin.web.product.model;

import com.liaojun.component.base.util.StringUtil;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/17 23:19
 */
public class ProductTypeTree {
    private String typeId;
    private String name;
    private List<ProductTypeTree> children;
    private Boolean spread;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductTypeTree> getChildren() {
        return children;
    }

    public void setChildren(List<ProductTypeTree> children) {
        this.children = children;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

}
