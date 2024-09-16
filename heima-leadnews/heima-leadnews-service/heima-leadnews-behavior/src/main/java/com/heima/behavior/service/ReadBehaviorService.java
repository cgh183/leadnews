package com.heima.behavior.service;

import com.heima.model.behavior.dtos.ReadBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

public interface ReadBehaviorService {

    /**
     * 阅读行为统计
     * @param dto
     * @return
     */
    public ResponseResult readBehavior(ReadBehaviorDto dto);
}
