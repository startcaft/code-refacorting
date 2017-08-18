package com.mq.redis.storage;

import com.basic.core.entity.vo.DicTypeVo;
import org.springframework.data.redis.core.RedisTemplate;

public class DicTypeStorage {

    private static final String DICTYPE_REDIS_LIST_KEY = "dic_type_list";//hash在redis数据库中的key
    private static final String DICTYPE_REDIS_KEY_PREFIX = "dicType_vo_";//hash中的map的key,value为Vo对象

    private RedisTemplate<String,DicTypeVo> redisTemplate;

    public DicTypeStorage(RedisTemplate<String, DicTypeVo> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addOrUpdate(DicTypeVo vo){
        {
            redisTemplate.opsForHash().put(DICTYPE_REDIS_LIST_KEY,DICTYPE_REDIS_KEY_PREFIX + vo.getId(),vo);
        }
    }

}
