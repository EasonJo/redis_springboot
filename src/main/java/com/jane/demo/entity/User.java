package com.jane.demo.entity;

import lombok.Data;

/**
 * 用户类
 * 登录名唯一作为键值
 * @author jane
 * @version 2018/03/09
 */
@Data
public class User {

    private String name; //用户姓名

    private String phoneNumber; //电话

    private String loginName;   //登录名（唯一）
}
