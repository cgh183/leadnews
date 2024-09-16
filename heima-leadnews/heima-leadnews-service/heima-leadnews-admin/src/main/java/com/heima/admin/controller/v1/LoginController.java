package com.heima.admin.controller.v1;

import com.heima.admin.service.AdUserService;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Api(value = "admin端用户登录",tags = "admin端用户登录")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private AdUserService adUserService;


    @PostMapping("/in")
    public ResponseResult login(@RequestBody AdUserDto dto){
        return adUserService.login(dto);
    }
}
