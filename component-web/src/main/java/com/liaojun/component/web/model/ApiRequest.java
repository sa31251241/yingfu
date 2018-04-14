package com.liaojun.component.web.model;

import com.alibaba.fastjson.JSONObject;
import com.liaojun.component.base.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChamIt-001 on 2017/10/24.
 */
public class ApiRequest {

    private static final Logger logger = LoggerFactory.getLogger(ApiRequest.class);

    private String method;
    private Map<String,Object> params;
    private String token;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void putParam(String key,Object value) {
        if(this.params == null){
            this.params = new HashMap<>();
        }
        this.params.put(key,value);
    }

    public void putParams(Map<String,Object> params) {
        if(params != null){
            if(this.params == null){
                this.params = params;
            } else {
                this.params.putAll(params);
            }
        }
    }

    public Object getParam(String key) {
        return params == null ? null : params.get(key);
    }

    public String getParamString(String key) { Object result = getParam(key); return result == null ? null : result.toString(); }

    public Integer getParamInt(String key) { Object result = getParam(key); return result == null ? null : Integer.valueOf(result.toString()); }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public <T> T populate(T obj) {
        try {
            BeanUtils.populate(obj,getParams());
            return obj;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public <T> T populate(T obj, Converter<Map,T> converter){
        try {
            obj = converter.convert(getParams());
            return obj;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public <T> T populate(String id,T obj) {
        try {
            if(params == null){
                params = new HashMap<>();
            }
            if(!StringUtil.isEmpty(id)) {
                params.put("id", id);
            }
            BeanUtils.populate(obj,getParams());
            return obj;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public Boolean methodEquals(String method){
        if(!StringUtil.isEmpty(this.method) && this.method.equals(method)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
