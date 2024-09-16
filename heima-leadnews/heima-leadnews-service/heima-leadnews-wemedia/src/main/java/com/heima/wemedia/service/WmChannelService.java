package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmChannel;

public interface WmChannelService extends IService<WmChannel> {

    /**
     * 查询所有频道
     * @return
     */
    public ResponseResult findAll();

    /**
     * 根据条件查询频道
     * @param dto
     * @return
     */
    ResponseResult findList(ChannelDto dto);

    /**
     * 新增频道
     * @param adChannel
     * @return
     */
    ResponseResult saveChannel(AdChannel adChannel);

    /**
     * 修改频道信息
     * @param adChannel
     * @return
     */
    ResponseResult updateChannel(AdChannel adChannel);

    /**
     * 根据id删除频道信息
     * @param id
     * @return
     */
    ResponseResult deleteChannel(Integer id);

}
