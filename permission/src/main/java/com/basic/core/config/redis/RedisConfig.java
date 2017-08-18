package com.basic.core.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private int minIdile;
    @Value("${spring.redis.pool.max-wait}")
    private int maxWait;

    //redis 连接池配置
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(maxActive);
            poolConfig.setMaxIdle(maxIdle);
            poolConfig.setMinIdle(minIdile);
            poolConfig.setMaxWaitMillis(maxWait);
            return poolConfig;
        }
    }

    //redis 连接工厂配置
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(@Autowired JedisPoolConfig jedisPoolConfig){
        {
            JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
            factory.setHostName(host);
            factory.setPort(port);
            factory.setTimeout(timeout);

            return factory;
        }
    }
    @Bean
    public RedisTemplate redisTemplate(@Autowired JedisConnectionFactory factory){
        {
            RedisTemplate redisTemplate = new RedisTemplate();
            redisTemplate.setConnectionFactory(factory);
            //1，使用JdkSerializationRedisSerializer类，进行对象和byte[]之间的相互转换，就像之前已经做得那样
            //2，我们使用字符串形式的key即可，因此：指定keySerializer为StringRedisSerializer类
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            return redisTemplate;
        }
    }
}