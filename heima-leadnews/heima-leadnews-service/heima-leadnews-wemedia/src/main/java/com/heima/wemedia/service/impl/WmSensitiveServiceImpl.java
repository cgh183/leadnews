package com.heima.wemedia.service.impl;
 
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.admin.dtos.SensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.model.wemedia.pojos.WmSensitive;
import com.heima.wemedia.mapper.WmSensitiveMapper;
import com.heima.wemedia.service.WmSensitiveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
 
import java.util.Date;
 

@Service
@Slf4j
public class WmSensitiveServiceImpl extends ServiceImpl<WmSensitiveMapper, WmSensitive> implements WmSensitiveService {
 
 
    /**
     * 敏感词分页条件查询
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findList(SensitiveDto dto) {
        //检查参数
        dto.checkParam();
 
        //分页查询
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<WmSensitive> queryWrapper = new LambdaQueryWrapper<>();
 
        //按照创建时间倒序查询
        queryWrapper.orderByDesc(WmSensitive::getCreatedTime);
 
        //按照敏感词模糊查询
        if (StringUtils.isNotBlank(dto.getName())) {
            queryWrapper.like(WmSensitive::getSensitives, dto.getName());
        }
 
        page = page(page, queryWrapper);
 
        //返回结果
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }
 
    /**
     * 根据id删除敏感词信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult delSensitive(Integer id) {
 
        //校验参数
        if (id == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
 
        //查询数据是否存在
        WmSensitive wmSensitive = getById(id);
        if (wmSensitive == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
 
        //根据id删除
        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
 
    /**
     * 保存敏感词
     * @param adSensitive
     * @return
     */
    @Override
    public ResponseResult saveSensitive(AdSensitive adSensitive) {
 
        //参数校验
        if (adSensitive == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
 
        //属性拷贝
        WmSensitive wmSensitive = new WmSensitive();
        BeanUtils.copyProperties(adSensitive, wmSensitive);
 
        //判断敏感词是否已经存在
        WmSensitive sensitive = getOne(Wrappers.<WmSensitive>lambdaQuery()
                .eq(WmSensitive::getSensitives, adSensitive.getSensitives()));
        if (sensitive != null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST);
        }
 
        wmSensitive.setCreatedTime(new Date());
        save(wmSensitive);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
 
    /**
     * 更新敏感词信息
     * @param adSensitive
     * @return
     */
    @Override
    public ResponseResult updateSensitive(AdSensitive adSensitive) {
 
        //校验参数
        if (adSensitive == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
 
        //属性拷贝
        WmSensitive wmSensitive = new WmSensitive();
        BeanUtils.copyProperties(adSensitive, wmSensitive);
 
        //修改信息
        updateById(wmSensitive);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}