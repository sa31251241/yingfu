package com.liaojun.component.web.auth.service;

import com.liaojun.component.web.auth.model.AuthToken;

/**
 * Created by ChamIt-001 on 2017/11/13.
 */
public interface AuthTokenManager {

    AuthToken createToken();

    AuthToken createToken(String id);

    AuthToken getToken(String id);

    void setDefaultInactiveInterval(Integer seconds);

    void validateTokens();

    void updateTokenAccessTime(String id);

    void dropToken(String id);

}
