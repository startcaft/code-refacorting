package com.basic.core.util;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Log4j Redis扩展
 */
public class RedisAppender extends AppenderSkeleton {

    private String host = "119.23.56.247";
    private int port = 7030;
    private String key = "zjw-log4j-log";
    private int dbIndex = 15;
    private int timeout = 10000;
    private int maxTotal = 200;
    private int maxIdle = 20;
    private int minIdle = 5;

    private JedisPool jedisPool;
    private boolean jedisHeath = true;

    @Override
    public void activateOptions() {
        super.activateOptions();

        //初始化redis客户端连接
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        if (minIdle > 0){
            poolConfig.setMinIdle(minIdle);
        }
        if (maxIdle > 0){
            poolConfig.setMaxIdle(maxIdle);
        }
        if (maxTotal > 0 || maxTotal != 8){
            poolConfig.setMaxTotal(maxTotal);
        }
        jedisPool = new JedisPool(poolConfig,host,port,timeout);
    }

    @Override
    protected void append(LoggingEvent event) {
        if (!jedisHeath) return;
        try {
            pushLog2Redis(layout.format(event));
        } catch (Exception e) {
            LogLog.error("redis append message error : ", e);
        }
    }

    @Override
    public void close() {
        LogLog.debug("do nothing...");
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }

    private void pushLog2Redis(String msg) {
        if (jedisPool != null) {
            Jedis jedis = jedisPool.getResource();
            jedis.select(dbIndex);
            Long listSize = jedis.lpush(key, msg);
            LogLog.debug(String.format("[%s] the log list size number is :%s", key, listSize));
            jedis.close();
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }
}
