package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmLoginDto;
import com.heima.model.wemedia.pojos.WmUser;

public interface WmUserService extends IService<WmUser> {

    /**
     * 自媒体端登录
     * @param dto
     * @return
     */
    public ResponseResult login(WmLoginDto dto);

    /**
     * 根据名称查询自媒体用户信息
     * @param name
     * @return
     */
    public ResponseResult findWmUserByName(String name);

    /**
     * 保存自媒体用户信息
     * @param wmUser
     */
    public ResponseResult saveWmUser(WmUser wmUser);
}