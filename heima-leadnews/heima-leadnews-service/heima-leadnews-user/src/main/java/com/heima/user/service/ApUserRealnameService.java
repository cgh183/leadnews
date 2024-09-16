package com.heima.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.AuthDto;
import com.heima.model.admin.pojos.ApUserRealname;
import com.heima.model.common.dtos.ResponseResult;


public interface ApUserRealnameService extends IService<ApUserRealname> {

    /**
     * 用户实名认证
     * @param authDto
     * @return
     */
    ResponseResult list(AuthDto authDto);

    ResponseResult updateStatus(AuthDto authDto, Short passAuth);
}
