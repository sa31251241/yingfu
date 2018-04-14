package com.liaojun.webadmin.system.model;

import com.liaojun.component.base.db.model.BaseModel;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/12 13:44
 */
@Table(name = "t_role")
public class Role extends BaseModel {

    private String name;

    private String desc;

    private String remark;

    private Integer accessAll;

    private Integer available;

    @Transient
    private List<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Integer getAccessAll() {
        return accessAll;
    }

    public void setAccessAll(Integer accessAll) {
        this.accessAll = accessAll;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
