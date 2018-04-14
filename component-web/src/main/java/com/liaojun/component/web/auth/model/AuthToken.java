package com.liaojun.component.web.auth.model;

import com.alibaba.fastjson.JSONObject;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChamIt-001 on 2017/11/13.
 */
public class AuthToken {

    private String id;
    private Long creationTime;
    private Long lastAccessedTime;
    private Integer maxInactiveInterval;
    private Map<String,Object> attributes;

    public AuthToken(String id){
        this.id = id;
        this.creationTime = System.currentTimeMillis();
        this.lastAccessedTime = this.creationTime;
        this.maxInactiveInterval = 0;
        this.attributes = new HashMap<>();
    }

    public long getCreationTime() {
        return creationTime;
    }

    public String getId() {
        return id;
    }

    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    public AuthToken setMaxInactiveInterval(int i) {
        this.maxInactiveInterval = i;
        return this;
    }

    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public Object getAttribute(String s) {
        return attributes.get(s);
    }

    public Enumeration<String> getAttributeKeys() {
        return (Enumeration<String>) attributes.keySet();
    }

    public AuthToken setAttribute(String s, Object o) {
        attributes.put(s,o);
        return this;
    }

    public AuthToken removeAttribute(String s) {
        attributes.remove(s);
        return this;
    }

    public void updateLastAccessTime(){
        this.lastAccessedTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
