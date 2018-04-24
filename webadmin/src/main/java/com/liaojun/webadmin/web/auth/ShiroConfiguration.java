package com.liaojun.webadmin.web.auth;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Author: yangzi
 * @Date: 2018/4/12 15:28
 */
@Configuration
public class ShiroConfiguration {

    //@Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/home");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        //表示可以匿名访问
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout","anon");
        filterChainDefinitionMap.put("/error*","anon");
        //表示需要认证才可以访问
        filterChainDefinitionMap.put("/*", "authc");
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/*.*", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    /**
     * 配置核心安全事务管理器
     * @param authRealm
     * @return
     */
    //@Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        System.out.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    /**
     * 配置自定义的权限登录器
     * @param matcher
     * @return
     */
    //@Bean(name="authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm=new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    /**
     * 配置自定义的密码比较器
     * @return
     */
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

}
