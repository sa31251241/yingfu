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

    public static enum DB_KEY {
        ID("id");
        private String value;
        DB_KEY(String value){this.value = value;}
        public String getValue(){return this.value;}
    }

    public void save(T t);

    public void insertAll(List<T> objs);

    public T get(String id);

    public T get(String key, Object value);

    public T get(Map<String, Object> paramMap);

    public boolean exist(String key, Object value);

    public boolean existExceptId(String id, String key, Object value);

    public T get(String key, Object value, SortRequest sortRequest);

    public T get(Map<String, Object> paramMap, SortRequest sortRequest);

    public void update(T t);

    public void update(String id, String key, Object value);

    public List<T> getList();

    public List<T> getList(String key, Object value);

    public List<T> getList(Map<String, Object> paramMap);

    public List<T> getList(String key, Object value, SortRequest sortRequest);

    public List<T> getList(Map<String, Object> paramMap, PageRequest pageRequest,SortRequest sortRequest);

    public List<T> getList(Map<String, Object> paramMap, SortRequest sortRequest);

    public int getSize(Map<String, Object> paramMap);

    public PageResult getPageResult(PageRequest pageRequest);

    public PageResult getPageResult(Map<String, Object> paramMap, PageRequest pageRequest);

    public PageResult getPageResult(Map<String, Object> paramMap, PageRequest pageRequest, SortRequest sortRequest);

    public List<T> getPageList(Map<String, Object> paramMap, PageRequest pageRequest, SortRequest sortRequest);

    public void delete(String id);

    public void delete(String key, Object value);

    public void delete(Map<String, Object> paramMap);

}
