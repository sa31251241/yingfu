package com.liaojun.webadmin.web.auth;

import com.liaojun.webadmin.system.model.Permission;
import com.liaojun.webadmin.system.model.Role;
import com.liaojun.webadmin.system.model.User;
import com.liaojun.webadmin.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/12 15:34
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        String username = utoken.getUsername();
        User user = userService.selectByName(username);
        //放入shiro.调用CredentialsMatcher检验密码
        return new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //获取session中的用户
        User user=(User) principal.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissions=new ArrayList<>();
        Role role = user.getRole();
        if(role != null) {
            List<Permission> permissionList = role.getPermissions();
            if(permissionList.size()>0) {
                for(Permission permission : permissionList) {
                    permissions.add(permission.getCode());
                }
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //将权限放入shiro中.
        info.addStringPermissions(permissions);
        info.addRole(role.getId());
        return info;
    }

}
