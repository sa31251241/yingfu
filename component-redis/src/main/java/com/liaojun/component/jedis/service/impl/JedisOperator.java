package com.liaojun.component.jedis.service.impl;

import com.liaojun.component.jedis.resource.JedisOperatorPool;
import com.liaojun.component.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;

/**
 * Created by ChamIt-001 on 2017/10/18.
 */
@Repository
public class JedisOperator implements RedisService {

    private static final Logger logger = LoggerFactory.getLogger(JedisOperator.class);

    @Resource
    private JedisOperatorPool operatorPool;

    public String get(String key) {
        try {
            return operatorPool.getJedis().get(key);
        } finally {
            operatorPool.release();
        }
    }

    public void set(String key, String value) {
        try {
            operatorPool.getJedis().set(key, value);
        } finally {
            operatorPool.release();
        }
    }

    public void set(String key, String value, Long seconds) {
        try {
            JedisCommands jedisCommands = operatorPool.getJedis();
            String nxxx = "nx";
            if(jedisCommands.exists(key)){
                nxxx = "xx";
            }
            jedisCommands.set(key,value,nxxx,"EX",seconds);
        } finally {
            operatorPool.release();
        }
    }

    public void del(String key) {
        try {
            operatorPool.getJedis().del(key);
        } finally {
            operatorPool.release();
        }
    }
}
