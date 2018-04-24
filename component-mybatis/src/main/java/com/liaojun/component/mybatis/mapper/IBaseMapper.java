package com.liaojun.component.mybatis.mapper;

import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.SortRequest;
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

    List<T> findList(T t, PageRequest pageRequest, SortRequest sortRequest);

    Integer findListCount(T t);
}