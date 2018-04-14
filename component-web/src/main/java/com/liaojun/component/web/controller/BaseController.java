package com.liaojun.component.web.controller;

import com.liaojun.component.base.util.StringUtil;
import com.liaojun.component.web.model.ApiRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ChamIt-001 on 2017/10/24.
 */
public class BaseController {

    private static final Logger baseLogger = LoggerFactory.getLogger(BaseController.class);

    protected HttpServletRequest getCurrentHttpRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected <T> T populate(T obj, ApiRequest apiRequest){
        try {
            BeanUtils.populate(obj,apiRequest.getParams());
            return obj;
        } catch (Exception e) {
            baseLogger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    protected Boolean isMethodEqual(ApiRequest apiRequest,String method){
        if(apiRequest == null || StringUtil.isEmpty(apiRequest.getMethod()) || !apiRequest.getMethod().equals(method)){
            return false;
        } else {
            return true;
        }
    }
}
