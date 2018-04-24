package com.liaojun.webadmin.system.service;

import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.webadmin.system.model.User;

/**
 * @Author: yangzi
 * @Date: 2018/4/10 10:40
 */
public interface UserService extends BaseService<User> {

    public User selectByName(String name);


}
