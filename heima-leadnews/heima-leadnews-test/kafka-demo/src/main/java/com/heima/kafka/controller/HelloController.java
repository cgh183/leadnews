package com.heima.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.heima.kafka.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private KafkaTemplate kafkaTemplate;


    @GetMapping(value = "/hello")
    public String hello(){
        //kafkaTemplate.send("itcast-topic","黑马程序员");
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        kafkaTemplate.send("user-topic", JSON.toJSONString(user));
        return "ok";
    }
}
