package com.jane.demo.conf;

/**
 * 设置Redis表,将缓存信息存入对应的表中
 *
 * @author jane
 * @version  2018/03/08
 */
public interface IRedisRepository<T> {
    String getRedisKey();
}
