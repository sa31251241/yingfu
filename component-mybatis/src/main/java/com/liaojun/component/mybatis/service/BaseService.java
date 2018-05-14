package com.liaojun.component.mybatis.service;

import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangzi
 * @Date: 2018/4/10 10:25
 */
public interface BaseService<T>{

    static enum DB_KEY {
        ID("id");
        private String value;
        DB_KEY(String value){this.value = value;}
        String getValue(){return this.value;}
    }

    void save(T t);

    void insertAll(List<T> objs);

    T get(String id);

    T get(String key, Object value);

    T get(Map<String, Object> paramMap);

    boolean exist(String key, Object value);

    boolean existExceptId(String id, String key, Object value);

    T get(String key, Object value, SortRequest sortRequest);

    T get(Map<String, Object> paramMap, SortRequest sortRequest);

    void update(T t);

    void update(String id, String key, Object value);

    List<T> getList();

    List<T> getList(String key, Object value);

    List<T> getList(Map<String, Object> paramMap);

    List<T> getList(String key, Object value, SortRequest sortRequest);

    List<T> getList(Map<String, Object> paramMap, PageRequest pageRequest,SortRequest sortRequest);

    List<T> getList(Map<String, Object> paramMap, SortRequest sortRequest);

    int getSize(Map<String, Object> paramMap);

    PageResult getPageResult(PageRequest pageRequest);

    PageResult getPageResult(Map<String, Object> paramMap, PageRequest pageRequest);

    PageResult getPageResult(Map<String, Object> paramMap, PageRequest pageRequest, SortRequest sortRequest);

    List<T> getPageList(Map<String, Object> paramMap, PageRequest pageRequest, SortRequest sortRequest);

    void delete(String id);

    void delete(String key, Object value);

    void delete(Map<String, Object> paramMap);

    T getBean(Map<String,Object> params);

    PageResult findList(T t, PageRequest pageRequest, SortRequest sortRequest);

    Map<String, Object> getBeanMap(T t);

    List<Map<String, Object>> getBeanMapList(List<T> list);
}
