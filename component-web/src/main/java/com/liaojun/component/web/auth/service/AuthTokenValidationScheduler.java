package com.liaojun.component.web.auth.service;

/**
 * Created by ChamIt-001 on 2017/11/13.
 */
public interface AuthTokenValidationScheduler {

    void setInterval(Integer seconds);

    void start(AuthTokenManager manager);
}
