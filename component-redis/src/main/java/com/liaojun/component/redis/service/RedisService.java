package com.liaojun.component.redis.service;

/**
 * Created by ChamIt-001 on 2017/10/18.
 */
public interface RedisService {

    public String get(String key);

    public void set(String key,String value);

    public void set(String key,String value,Long seconds);

    public void del(String key);
}
