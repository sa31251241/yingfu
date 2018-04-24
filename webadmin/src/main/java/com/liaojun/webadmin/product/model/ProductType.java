package com.liaojun.webadmin.product.model;

import com.liaojun.component.base.db.model.BaseModel;
import com.liaojun.webadmin.web.product.model.ProductTypeTree;
import org.springframework.util.CollectionUtils;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * 物品类别
 * @Author: yangzi
 * @Date: 2018/4/13 10:07
 */
@Table(name = "t_product_type")
public class ProductType extends BaseModel{
    @Id
    private String id;
    private String pid;
    @Transient
    private String pname;
    private String name;
    private String remark;

    @Transient
    private List<ProductType> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
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

    public List<ProductType> getChildren() {
        return children;
    }

    public List<ProductTypeTree> getChildrenTree() {
        if(CollectionUtils.isEmpty(getChildren())){
            return null;
        }
        List<ProductTypeTree> productTypeTreeList = new ArrayList<>();
        for (ProductType productType : getChildren()) {
            ProductTypeTree productTypeTree = new ProductTypeTree();
            productTypeTree.setTypeId(productType.getId());
            productTypeTree.setName(productType.getName());
            productTypeTree.setSpread(true);
            productTypeTree.setChildren(productType.getChildrenTree());
            productTypeTreeList.add(productTypeTree);
        }
        return productTypeTreeList;
    }

    public void setChildren(List<ProductType> children) {
        this.children = children;
    }
}
