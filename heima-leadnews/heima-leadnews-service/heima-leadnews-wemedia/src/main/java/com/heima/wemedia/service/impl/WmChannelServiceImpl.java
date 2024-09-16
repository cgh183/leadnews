package com.heima.wemedia.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.wemedia.mapper.WmChannelMapper;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.service.WmChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
@Slf4j
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel> implements WmChannelService {

    @Autowired
    private WmNewsMapper wmNewsMapper;

    /**
     * 查询所有频道
     * @return
     */
    @Override
    public ResponseResult findAll() {
        return  ResponseResult.okResult(list());
    }

    @Override
    public ResponseResult findList(ChannelDto dto) {
        // 校验参数
        dto.checkParam();
        //分页条件查询
        IPage page = new Page(dto.getPage(),dto.getSize());
        LambdaQueryWrapper<WmChannel> queryWrapper = new LambdaQueryWrapper<>();

        //按照时间倒序查询
        queryWrapper.orderByDesc(WmChannel::getCreatedTime);
        //根据名称模糊查询
        if (StringUtils.isNotBlank(dto.getName())) {
            queryWrapper.like(WmChannel::getName, dto.getName());
        }

        // TODO 根据状态精确查询



        //根据默认排序进行排序
        queryWrapper.orderByAsc(WmChannel::getOrd);

        page = page(page, queryWrapper);
        //返回结果
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }

    @Override
    public ResponseResult saveChannel(AdChannel adChannel) {
        //属性拷贝
        WmChannel wmChannel = new WmChannel();
        BeanUtils.copyProperties(adChannel, wmChannel);

        //判断名称是否重复
        LambdaQueryWrapper<WmChannel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WmChannel::getName, wmChannel.getName());
        int count = count(queryWrapper);
        if (count > 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST,"频道名称已存在");
        }

        //设置默认属性值
        wmChannel.setOrd(1);
        wmChannel.setCreatedTime(new Date());
        wmChannel.setIsDefault(true);

        //保存数据
        save(wmChannel);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(),"新增频道成功");
    }

    @Override
    public ResponseResult updateChannel(AdChannel adChannel) {
        //属性拷贝
        WmChannel wmChannel = new WmChannel();
        BeanUtils.copyProperties(adChannel, wmChannel);

        //判断当前频道是否被引用
        Integer count = wmNewsMapper
                .selectCount(Wrappers.<WmNews>lambdaQuery().eq(WmNews::getChannelId, wmChannel.getId()));
        if (count > 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"该频道已被引用不能禁用");
        }

        updateById(wmChannel);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);

    }

    @Override
    public ResponseResult deleteChannel(Integer id) {
        //校验参数
        if (id == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //判断是否被禁用
        WmChannel wmChannel = getById(id);
        if (wmChannel.getStatus()) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"频道启用中无法删除");
        }

        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
