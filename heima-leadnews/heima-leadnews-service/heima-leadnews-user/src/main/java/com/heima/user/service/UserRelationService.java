package com.heima.user.service;

public interface UserRelationService {

    /**
     * 用户关注与取关
     * @param dto
     * @return
     */
    public ResponseResult follow(UserRelationDto dto);
}
