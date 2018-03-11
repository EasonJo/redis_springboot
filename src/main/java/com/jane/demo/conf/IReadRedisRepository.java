package com.jane.demo.conf;

import java.util.List;
import java.util.Set;

/**
 * 缓存读操作
 *
 * @author jane
 * @version  2018/03/08
 */
public interface IReadRedisRepository<T> extends IRedisRepository<T> {

    /**
     * 获取键值key的Value
     * @param key 键
     * @return
     */
    T get(String key);

    /**
     * 获取所有缓存Value信息
     * @return
     */
    List<T> getAll();

    /**
     * 获取所有键值key
     * @return
     */
    Set<String> getKeys();

    /**
     * 键值key是否存在
     * @param key 键
     * @return
     */
    boolean isKeyExists(String key);

    /**
     * Redis缓存计数器
     * @return
     */
    long count();
}
