package com.liaojun.webadmin.system.model;

import com.liaojun.component.base.db.model.BaseModel;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/12 13:44
 */
@Table(name = "t_role")
public class Role extends BaseModel {

    @Id
    private String id;

    private String name;

    private String desc;

    private String remark;


    @Transient
    private List<Permission> permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
