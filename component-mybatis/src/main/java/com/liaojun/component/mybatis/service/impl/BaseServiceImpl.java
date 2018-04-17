package com.liaojun.component.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.liaojun.component.base.constant.ComponentBaseConstant;
import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.base.util.BeanUtil;
import com.liaojun.component.base.util.IdGenerateUtil;
import com.liaojun.component.base.util.StringUtil;
import com.liaojun.component.mybatis.constant.ComponentMyBatisConstant;
import com.liaojun.component.mybatis.service.BaseService;
import com.liaojun.component.mybatis.mapper.IBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yangzi
 * @Date: 2018/4/10 10:26
 */
public abstract class BaseServiceImpl<T> implements BaseService<T>{

    @Autowired
    private IBaseMapper<T> baseMapper;

    /**
     * 模板方法获取当前实体的class
     * @return
     */
    public abstract Class getEntityClass();

    @Override
    public void save(T t) {
        setBaseProperties(t);
        baseMapper.insert(t);
    }

    @Override
    public void insertAll(List<T> list) {
        baseMapper.insertList(list);
    }

    @Override
    public T get(String id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public T get(String key, Object value) {
        PageHelper.startPage(1,1);
        Condition condition = getCondition();
        List<T> ts = baseMapper.selectByExample(condition.createCriteria().andEqualTo(key, value));
        return returnOne(ts);
    }

    @Override
    public T get(Map<String, Object> paramMap) {
        PageHelper.startPage(1,1);
        List<T> ts = baseMapper.selectByExample(CriteriaUtil.combineParam(paramMap,getEntityClass()));
        return returnOne(ts);
    }

    @Override
    public boolean exist(String key, Object value) {
        return get(key,value) == null ? false:true;
    }

    @Override
    public boolean existExceptId(String id, String key, Object value) {
        Map<String,Object> params = new HashMap<>(10);
        params.put(StringUtil.concat(ComponentMyBatisConstant.DB_KEY.ID.getValue(),CriteriaUtil.DB_OPERATOR_MATCHES.NE.getValue()),id);
        params.put(key,value);
        return CollectionUtils.isEmpty(baseMapper.selectByExample(CriteriaUtil.combineParam(params,getEntityClass())))?false:true;
    }

    @Override
    public T get(String key, Object value, SortRequest sortRequest) {
        PageHelper.startPage(1,1);
        Condition condition = getCondition();
        setSort(condition,sortRequest);
        condition.createCriteria().andEqualTo(key,value);
        List<T> ts = baseMapper.selectByExample(condition);
        return returnOne(ts);
    }

    @Override
    public T get(Map<String, Object> paramMap, SortRequest sortRequest) {
        PageHelper.startPage(1,1);
        Condition condition = CriteriaUtil.combineParam(paramMap, getEntityClass());
        setSort(condition,sortRequest);
        List<T> ts = baseMapper.selectByExample(condition);
        return returnOne(ts);
    }

    @Override
    public void update(T t) {
        baseMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public void update(String id, String key, Object value) {
        T t = get(id);
        try {
            BeanUtil.setProperty(t,key,value);
            update(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getList() {
        return baseMapper.selectAll();
    }

    @Override
    public List<T> getList(String key, Object value) {
        Condition condition = getCondition();
        condition.createCriteria().andEqualTo(key,value);
        return baseMapper.selectByExample(condition);
    }

    @Override
    public List<T> getList(Map<String, Object> paramMap) {
        return baseMapper.selectByExample(CriteriaUtil.combineParam(paramMap,getEntityClass()));
    }

    @Override
    public List<T> getList(String key, Object value, SortRequest sortRequest) {
        Condition condition = getCondition();
        condition.createCriteria().andEqualTo(key,value);
        setSort(condition,sortRequest);
        return baseMapper.selectByExample(condition);
    }


    @Override
    public List<T> getList(Map<String, Object> paramMap, SortRequest sortRequest) {
        Condition condition = CriteriaUtil.combineParam(paramMap,getEntityClass());
        setSort(condition,sortRequest);
        return baseMapper.selectByExample(condition);
    }

    @Override
    public List<T> getList(Map<String, Object> paramMap, PageRequest pageRequest,SortRequest sortRequest) {
        Condition condition = CriteriaUtil.combineParam(paramMap,getEntityClass());
        setSort(condition,sortRequest);
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getLimit());
        return baseMapper.selectByExample(condition);
    }


    @Override
    public int getSize(Map<String, Object> paramMap) {
        return baseMapper.selectCountByExample(CriteriaUtil.combineParam(paramMap,getEntityClass()));
    }

    @Override
    public PageResult getPageResult(PageRequest pageRequest) {
        return new PageResult(pageRequest,getSize(null),getPageList(null,pageRequest,null));
    }

    @Override
    public PageResult getPageResult(Map<String, Object> paramMap, PageRequest pageRequest) {
        return new PageResult(pageRequest,getSize(paramMap),getPageList(paramMap,pageRequest,null));
    }

    @Override
    public PageResult getPageResult(Map<String, Object> paramMap, PageRequest pageRequest, SortRequest sortRequest) {
        return new PageResult(pageRequest,getSize(paramMap),getPageList(paramMap,pageRequest,sortRequest));
    }

    @Override
    public List<T> getPageList(Map<String, Object> paramMap, PageRequest pageRequest, SortRequest sortRequest) {
        return getList(paramMap,pageRequest,sortRequest);
    }

    @Override
    public void delete(String id) {
        baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void delete(String key, Object value) {
        Condition condition = getCondition();
        condition.createCriteria().andEqualTo(key,value);
        baseMapper.deleteByExample(condition);
    }

    @Override
    public void delete(Map<String, Object> paramMap) {
        baseMapper.deleteByExample(CriteriaUtil.combineParam(paramMap,getEntityClass()));
    }

    private void setBaseProperties(T t){
        try {
            BeanUtil.setProperty(t, ComponentMyBatisConstant.DB_KEY.ID.getValue(), IdGenerateUtil.getInstance().nextId().toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private T returnOne(List<T> list){
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    private Condition getCondition(){
        return new Condition(getEntityClass());
    }

    private void setSort(Condition condition,SortRequest sortRequest){
        if(sortRequest!=null && !StringUtil.isEmpty(sortRequest.getSortKey()) && !StringUtil.isEmpty(sortRequest.getSortDirection())){
            Example.OrderBy orderBy = condition.orderBy(sortRequest.getSortKey());
            if(ComponentBaseConstant.SORT_REQUEST_DIRECT.ASC.getValue().equals(sortRequest.getSortDirection())){
                orderBy.asc();
            }else{
                orderBy.desc();
            }
        }
    }
}
