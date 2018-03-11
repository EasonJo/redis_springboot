package com.jane.demo.service;

import com.jane.demo.entity.User;
import com.jane.demo.redis.UserRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jane
 * @version 2018/03/09
 */
@Service
public class UserService {

    @Autowired
    private UserRedis userRedis;

    /**
     * 保存用户（登录名唯一，作为键值）
     * @param user 用户信息
     */
    public void saveUser(User user){
        userRedis.put(user.getLoginName(), user);
    }

    /**
     * 根据登录名获取用户信息
     * @param loginName 登录名
     * @return 用户信息
     */
    public User getUser(String loginName){
       return userRedis.get(loginName);
    }

    /**
     * 判断当前登录名称是否存在
     * @param loginName 登录名
     * @return 存在返回true，否则false
     */
    public boolean isExsist(String loginName){
        return userRedis.isKeyExists(loginName);
    }

    /**
     * 获取所有用户信息
     * @return 用户集
     */
    public List<User> findAll(){
       return userRedis.getAll();
    }
}
