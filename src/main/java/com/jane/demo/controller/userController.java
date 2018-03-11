package com.jane.demo.controller;

import com.jane.demo.entity.User;
import com.jane.demo.model.JsonModel;
import com.jane.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 * @author jane
 * @version 2018/03/09
 */
@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public JsonModel test(){
        return new JsonModel(200, "测试成功");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonModel create(User user){
        if (!userService.isExsist(user.getLoginName())){
            userService.saveUser(user);
            return new JsonModel(200,"成功添加！");
        }else {
            return new JsonModel(304,"已存在，无法添加！");
        }
    }



}
