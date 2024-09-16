package com.heima.behavior.service;

import com.heima.model.behavior.dtos.LikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

public interface LikesBehaviorService {
    /**
     * 用户点赞与取消
     * @param dto
     * @return
     */
    public ResponseResult like(LikesBehaviorDto dto);

}
