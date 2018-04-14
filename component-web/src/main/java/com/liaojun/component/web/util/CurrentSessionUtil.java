package com.liaojun.component.web.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by ChamIt-001 on 2017/10/24.
 */
public class CurrentSessionUtil {

    public static HttpSession getSession(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    public static void setAttribute(String key,Object value){
        getSession().setAttribute(key,value);
    }

    public static Object getAttribute(String key){
        return getSession().getAttribute(key);
    }

    public static void removeAttribute(String key){
        getSession().removeAttribute(key);
    }
}
