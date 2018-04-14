package com.liaojun.component.web.auth.service.impl;

import com.liaojun.component.base.common.model.BusinessException;
import com.liaojun.component.base.util.IdGenerateUtil;
import com.liaojun.component.web.auth.model.AuthToken;
import com.liaojun.component.web.auth.service.AuthTokenManager;
import com.liaojun.component.web.auth.service.AuthTokenValidationScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ChamIt-001 on 2017/11/13.
 */
@Service("defaultAuthTokenManager")
public class DefaultAuthTokenManager implements AuthTokenManager {

    private Map<String,AuthToken> tokenMap;
    private Integer defaultInactiveInterval = 3600;
    @Autowired(required = false)
    private AuthTokenValidationScheduler validationScheduler;
    private Boolean newTokenCheckExist = false;

    public DefaultAuthTokenManager(){
        tokenMap = new HashMap<>();
    }

    @PostConstruct
    public void init(){
        if(validationScheduler != null){
            this.validationScheduler.start(this);
        }
    }

    @Override
    public AuthToken createToken() {
        return createToken(IdGenerateUtil.getInstance().nextId().toString());
    }

    @Override
    public AuthToken createToken(String id) {
        AuthToken newToken = new AuthToken(id).setMaxInactiveInterval(defaultInactiveInterval);
        if(newTokenCheckExist && tokenMap.containsKey(id)){
            throw new BusinessException("create authToken error: tokenId exists");
        }
        tokenMap.put(id,newToken);
        return getToken(id);
    }

    @Override
    public AuthToken getToken(String id) {
        if(isValidToken(id)){
            return tokenMap.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void setDefaultInactiveInterval(Integer seconds) {
        defaultInactiveInterval = seconds;
    }

    @Override
    public void validateTokens() {
        Iterator<Map.Entry<String,AuthToken>> iterator = tokenMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,AuthToken> entry = iterator.next();
            if(!isValidToken(entry.getKey())){
                iterator.remove();
            }
        }
    }

    @Override
    public void updateTokenAccessTime(String id) {
        tokenMap.get(id).updateLastAccessTime();
    }

    @Override
    public void dropToken(String id) {
        tokenMap.remove(id);
    }

    private Boolean isValidToken(String id){
        AuthToken authToken = tokenMap.get(id);
        if(authToken == null
                || (authToken.getMaxInactiveInterval() >= 0
                    && (authToken.getLastAccessedTime()/1000 + authToken.getMaxInactiveInterval()) < System.currentTimeMillis()/1000)){
            return false;
        } else {
            return  true;
        }
    }

    public void setValidationScheduler(AuthTokenValidationScheduler validationScheduler) {
        this.validationScheduler = validationScheduler;
        this.validationScheduler.start(this);
    }

    public void setNewTokenCheckExist(Boolean newTokenCheckExist) {
        this.newTokenCheckExist = newTokenCheckExist;
    }
}
