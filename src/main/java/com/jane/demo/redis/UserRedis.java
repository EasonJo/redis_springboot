package com.jane.demo.redis;

import com.jane.demo.conf.RedisKey;
import com.jane.demo.conf.impl.AbstractRedisRepository;
import com.jane.demo.entity.User;
import org.springframework.stereotype.Component;

/**
 * 用户模块
 * @author jane
 * @version 2018/03/09
 */
@Component
public class UserRedis extends AbstractRedisRepository<User> {
    @Override
    public String getRedisKey() {
        return RedisKey.USER_KEY;
    }

    @Override
    protected Class<User> getSerializeClass() {
        return User.class;
    }
}
