package com.heima.user.controller.v1;

import com.heima.common.constants.UserConstants;
import com.heima.model.admin.dtos.AuthDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.user.service.ApUserRealnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class ApUserRealnameController {
 
    @Autowired
    private ApUserRealnameService apUserRealnameService;
 
    @PostMapping("/list")
    public ResponseResult list(@RequestBody AuthDto authDto) {
        return apUserRealnameService.list(authDto);
    }

    @PostMapping("/authPass")
    public ResponseResult authPass(@RequestBody AuthDto authDto) {
        return apUserRealnameService.updateStatus(authDto, UserConstants.PASS_AUTH);
    }

    @PostMapping("/authFail")
    public ResponseResult authFail(@RequestBody AuthDto authDto) {
        return apUserRealnameService.updateStatus(authDto, UserConstants.FAIL_AUTH);
    }
}