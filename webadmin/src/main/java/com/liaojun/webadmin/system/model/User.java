package com.liaojun.webadmin.system.model;

import com.liaojun.component.base.db.model.BaseModel;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/12 13:41
 */
@Table(name = "t_user")
public class User extends BaseModel{
    private String userName;

    private String password;

    private String roleId;

    private String roleName;

    @Transient
    private Role role;

    @Transient
    private List<Permission> permissions;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
