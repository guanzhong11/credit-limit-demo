package com.demo.creditlimit.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


/**
 * Redis 分布式锁
 * 不连 redis，用一个简单方法模拟
 */
@Component
public class RedisLock {

//    @Autowired
//    StringRedisTemplate redisTemplate;
//
//    private static final int DEFAULT_EXPIRE_SECONDS = 10;
//
//    public boolean lock(String key, int expireSeconds) {
//        final Boolean res = redisTemplate.opsForValue().setIfAbsent(
//                key, String.valueOf(System.currentTimeMillis()), expireSeconds, TimeUnit.SECONDS);
//        return res != null && res;
//    }
//
//    public boolean lock(String key) {
//        return lock(key, DEFAULT_EXPIRE_SECONDS);
//    }
//
//    public void unlock(String key) {
//        redisTemplate.delete(key);
//    }

    public boolean lock(String key) {
        return true;
    }

    public void unlock(String key) {

    }

}
