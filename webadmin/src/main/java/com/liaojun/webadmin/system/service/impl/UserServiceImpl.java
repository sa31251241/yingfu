package com.liaojun.webadmin.system.service.impl;

import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.system.mapper.UserMapper;
import com.liaojun.webadmin.system.model.User;
import com.liaojun.webadmin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yangzi
 * @Date: 2018/4/10 10:40
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public Class getEntityClass() {
        return User.class;
    }
}
