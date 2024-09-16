package com.heima.behavior.service;

import com.heima.model.behavior.dtos.UnLikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

public interface UnlikesBehaviorService {

    public ResponseResult unLike(UnLikesBehaviorDto dto);
}
