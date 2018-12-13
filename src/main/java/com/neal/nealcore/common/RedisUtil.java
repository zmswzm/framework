package com.neal.nealcore.common;


import com.neal.nealcore.exception.beans.impl.SystemExceptionDesc;
import com.neal.nealcore.exception.impl.internal.framework.FrameworkInternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
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
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","指定缓存失效时间异常",e));
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
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","判断key是否存在异常",e));
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
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","普通缓存放入异常",e));
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
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","普通缓存放入并设置时间异常",e));
        }
    }

    //递增
    public long incr(String key, long delta) {
        if(delta < 0){
            throw new FrameworkInternalException(new SystemExceptionDesc("递增因子必须大于0"));
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    //递减
    public long decr(String key, long delta) {
        if(delta < 0){
            throw new FrameworkInternalException(new SystemExceptionDesc("递减因子必须大于0"));
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","hmset异常",e));
        }
    }

    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if(time > 0) {
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","hmset(time)异常",e));
        }
    }

    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","hset(table)异常",e));
        }
    }

    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if(time > 0) {
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","hset(table&time)异常",e));
        }
    }

    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","sGet异常",e));
        }
    }

    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","sHasKey异常",e));
        }
    }

    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","sHasKey异常",e));
        }
    }

    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0){
                expire(key, time);
            }
            return count;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","sSetAndTime异常",e));
        }
    }

    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","sGetSetSize异常",e));
        }
    }

    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","setRemove异常",e));
        }
    }

    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","lGet异常",e));
        }
    }

    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","lGetListSize异常",e));
        }
    }

    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","lGetIndex异常",e));
        }
    }

    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","lSet异常",e));
        }
    }

    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","lSet(time)异常",e));
        }
    }

    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","rightPushAll异常",e));
        }
    }

    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","rightPushAll(time)异常",e));
        }
    }

    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","lUpdateIndex异常",e));
        }
    }

    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        }catch (Exception e){
            throw new FrameworkInternalException(new SystemExceptionDesc("0002","lRemove异常",e));
        }
    }
}
