package com.jane.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.jane.demo.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 用户控制层测试
 *
 * @author jane
 * @version 2018/03/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class userControllerTest {
    /** 模拟MVC对象*/
    private MockMvc mockMvc;

    /**注入Web应用上下文*/
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    /**
     * 测试用户的添加
     * @throws Exception
     */
    @Test
    public void testCreate()throws Exception{
        User user = new User();
        user.setName("JunitTest");
        user.setPhoneNumber("1234531");
        user.setLoginName("junit");

        MvcResult result = mockMvc.perform(post("/user/create?name=JunitTest&phoneNumber=1234531&loginName=junit").contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(user)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    /**
     * 测试web项目是否部署成功
     * @throws Exception
     */
    @Test
    public void testWeb()throws Exception {
        String result = mockMvc.perform(get("/user/test").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
        System.out.println("-------返回的JSON:" + result);

    }
}
