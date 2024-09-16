package com.heima.model.admin.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

@Data
public class ChannelDto extends PageRequestDto {

    /**
     * 频道名称
     */
    private String name;
}
