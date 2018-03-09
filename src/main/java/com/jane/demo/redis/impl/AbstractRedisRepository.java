package com.jane.demo.redis.impl;

import com.alibaba.fastjson.JSON;
import com.jane.demo.redis.IReadRedisRepository;
import com.jane.demo.redis.IWriteRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存操作
 *
 * @author jane
 * @version 2018/03/08
 */
public abstract class AbstractRedisRepository<T> implements IReadRedisRepository<T>, IWriteRedisRepository<T> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private HashOperations<String, String, String> hashOperations;

    /**
     * 插入缓存，并不过期
     * @param key 键
     * @param t 值
     */
    @Override
    public void put(String key, T t) {
        put(key, t, -1);
    }

    /**
     * 插入缓存
     * @param key 键
     * @param t 值
     * @param expire 过期时间,如果为-1,则不设置过期时间
     */
    @Override
    public void put(String key, T t, long expire) {
        hashOperations.put(getRedisKey(), key, JSON.toJSONString(t));
        if (expire != -1) {
            stringRedisTemplate.expire(getRedisKey(), expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 移除缓存
     * @param key
     */
    @Override
    public void remove(String key) {
        hashOperations.delete(getRedisKey(), key);
    }

    /**
     * 清空缓存
     */
    @Override
    public void empty() {
        Set<String> set = hashOperations.keys(getRedisKey());
        set.forEach(key -> hashOperations.delete(getRedisKey(), key));
    }

    /**
     * 获取缓存Value
     * @param key
     * @return
     */
    @Override
    public T get(String key) {
        return JSON.parseObject(hashOperations.get(getRedisKey(), key), getSerializeClass());
    }

    /**
     * 序列化对象
     * @return
     */
    protected abstract Class<T> getSerializeClass();

    /**
     * 获取所有缓存Value
     * @return
     */
    @Override
    public List<T> getAll() {
        return JSON.parseArray(JSON.toJSONString(hashOperations.values(getRedisKey())), getSerializeClass());
    }

    /**
     * 获取所有缓存的key
     * @return
     */
    @Override
    public Set<String> getKeys() {
        return hashOperations.keys(getRedisKey());
    }

    /**
     * 键值key是否存在
     * @param key 键
     * @return
     */
    @Override
    public boolean isKeyExists(String key) {
        return hashOperations.hasKey(getRedisKey(), key);
    }

    /**
     * 缓存计数器
     * @return
     */
    @Override
    public long count() {
        return hashOperations.size(getRedisKey());
    }

}
