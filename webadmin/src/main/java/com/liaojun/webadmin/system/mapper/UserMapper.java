package com.liaojun.webadmin.system.mapper;

import com.liaojun.component.mybatis.mapper.IBaseMapper;
import com.liaojun.webadmin.system.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface UserMapper extends IBaseMapper<User> {

    User selectByName(String name);
}
