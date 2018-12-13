package com.neal.nealcore.common;


import com.neal.nealcore.exception.beans.impl.SystemExceptionDesc;
import com.neal.nealcore.exception.impl.internal.framework.FrameworkInternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //指定缓存失效时间
    public boolean expire(String key, long time){
        try{
            if (time > 0) {
                return redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","RedisUtil.expire异常",e));
        }
        return false;
    }

    //根据key 获取过期时间
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    //判断key是否存在
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","RedisUtil.hasKey异常",e));
        }
    }

    //删除缓存
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //普通缓存获取
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    //普通缓存放入
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","普通RedisUtil.set异常",e));
        }
    }

    //普通缓存放入并设置时间
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0){
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
                return true;
            }else {
                return set(key, value);
            }
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","设置时间RedisUtil.set异常",e));
        }
    }
}
