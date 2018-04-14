package com.liaojun.component.mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.provider.ExampleProvider;

import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/10 10:23
 */
public interface IBaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

    @Select("select * from abc where id=#{id}")
    List<T> listAll(String id);
}