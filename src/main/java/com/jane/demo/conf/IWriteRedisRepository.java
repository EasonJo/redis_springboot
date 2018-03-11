package com.jane.demo.conf;

/**
 * 缓存写操作
 * @param <T>
 */
public interface IWriteRedisRepository<T> extends IRedisRepository<T> {
    /**
     * 插入缓存，并不过期
     * @param key 键
     * @param t 值
     */
    void put(String key, T t);

    /**
     *插入缓存
     * @param key 键
     * @param t 值
     * @param expire 过期时间,如果为-1,则不设置过期时间
     */
    void put(String key, T t, long expire);

    /**
     * 移除缓存
     * @param key
     */
    void remove(String key);

    /**
     * 清空缓存
     */
    void empty();

}
