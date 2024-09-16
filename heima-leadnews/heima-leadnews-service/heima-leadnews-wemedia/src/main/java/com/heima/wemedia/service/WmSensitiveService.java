package com.heima.wemedia.service;
 
import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.SensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmSensitive;
 

public interface WmSensitiveService extends IService<WmSensitive> {
 
    /**
     * 敏感词列表
     * @param dto
     * @return
     */
    ResponseResult findList(SensitiveDto dto);
 
    /**
     * 根据id删除敏感词信息
     * @param id
     * @return
     */
    ResponseResult delSensitive(Integer id);
 
    /**
     * 保存敏感词
     * @param adSensitive
     * @return
     */
    ResponseResult saveSensitive(AdSensitive adSensitive);
 
    /**
     * 更新敏感词信息
     * @param adSensitive
     * @return
     */
    ResponseResult updateSensitive(AdSensitive adSensitive);
}