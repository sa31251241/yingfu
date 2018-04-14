package com.liaojun.component.web.auth.util;

import com.liaojun.component.web.auth.constant.WebAuthConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ChamIt-001 on 2017/11/14.
 */
public class AuthTokenUtil {

    public static String getAuthTokenId(HttpServletRequest request){
        return request.getHeader(WebAuthConstant.AUTH_TOKEN.HEADER_KEY);
    }
}
