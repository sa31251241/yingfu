package com.liaojun.component.jedis.resource;

import com.liaojun.component.base.util.StringUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ChamIt-001 on 2017/10/18.
 */
@Repository
public class JedisOperatorPool {

    private static final Logger logger = LoggerFactory.getLogger(JedisOperatorPool.class);

    private static JedisPool jp;
    private static JedisPoolConfig config;
    private JedisCluster jedisCluster;
    private boolean isCluster = false;//是否集群标志
    private static int connErrTimes = 0;//连续连接错误次数
    private static int connErrMaxTimes = 5;//最大连续连接错误次数，超出则重新创建连接池

    public JedisOperatorPool(String host, int port, String auth, int timeout, int maxTotal, int maxIdle, long maxWaitMillis, String sCluster) {
        logger.info("[redis-连接池]- 开始创建连接池...");
        config = new JedisPoolConfig(host,port,auth,timeout,maxTotal,maxIdle,maxWaitMillis,sCluster);
        this.isCluster = !StringUtils.isEmpty(sCluster) && BooleanUtils.toBoolean(sCluster);
        if (this.isCluster) {
            HostAndPort hp0 = new HostAndPort(host, port);
            Set<HostAndPort> hps = new HashSet<HostAndPort>();
            hps.add(hp0);
            this.jedisCluster = new JedisCluster(hps, config);
            logger.info("connnect cluster by : ip = [{}] ; port = [{}]", host, port);
        } else {
            if (StringUtil.isEmpty(auth)) {
                jp = new JedisPool(config, host, port, timeout);
            } else {
                jp = new JedisPool(config, host, port, timeout, auth);
            }
        }
    }

    public JedisCommands getJedis() {
        if (isCluster) {
            return this.jedisCluster;
        }
        if (jp == null) {
            return null;
        }
        try {
            Jedis jedis = jp.getResource();
            if (connErrTimes > 0) {
                connErrTimes = 0;
            }
            return jedis;
        } catch (Exception e) {
            if (++ connErrTimes >= connErrMaxTimes) {
                logger.warn("[redis-连接池]- 获取连接错误(" + connErrTimes + "/" + connErrMaxTimes + "),连续错误超限，即将自动重启...");
                connErrTimes = 0;
                restart();
            } else {
                logger.warn("[redis-连接池]- 获取连接错误(" + connErrTimes + "/" + connErrMaxTimes + ")");
            }
            return null;
        }
    }

    /**
     * 释放连接
     */
    public void release() {
        if (!isCluster && jp != null) {
            jp.close();
        }
    }

    /**
     * 销毁连接池
     */
    public void destroy() {
        logger.warn("[redis-连接池]- 开始销毁连接池...");
        if (jp != null) {
            jp.destroy();
        }
    }

    /**
     * 重启连接池
     */
    public void restart() {
        logger.warn("[redis-连接池]- 开始重启连接池...");
        String info = "SUSS";
        try {
            destroy();
            new JedisOperatorPool(config.getHost(), config.getPort(), config.getAuth(), config.getTimeout(),
                    config.getMaxTotal(), config.getMaxIdle(), config.getMaxWaitMillis(), config.getIsCluster());
        } catch (Exception e) {
            info = e.getMessage();
        } finally {
            logger.warn("[redis-连接池]- 重启连接池完成。重启状况：" + info);
        }
    }
}

class JedisPoolConfig extends GenericObjectPoolConfig {
    private String host;
    private Integer port;
    private String auth;
    private Integer timeout;
    private String isCluster;

    public JedisPoolConfig(String host, int port, String auth, int timeout, int maxTotal, int maxIdle, long maxWaitMillis, String sCluster){
        setHost(host);
        setPort(port);
        setAuth(auth);
        setTimeout(timeout);
        setMaxTotal(maxTotal);
        setMaxIdle(maxIdle);
        setMaxWaitMillis(maxWaitMillis);
        setIsCluster(isCluster);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getIsCluster() {
        return isCluster;
    }

    public void setIsCluster(String isCluster) {
        this.isCluster = isCluster;
    }
}