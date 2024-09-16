package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.NewsAuthDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmNews;


public interface WmNewsService extends IService<WmNews> {
    /**
     * 查询文章
     * @param dto
     * @return
     */
    public ResponseResult findAll(WmNewsPageReqDto dto);

    /**
     * 发布修改文章或保存为草稿
     * @param dto
     * @return
     */
    public ResponseResult submitNews(WmNewsDto dto);

    /**
     * 文章上下架
     * @param dto
     * @return
     */
    ResponseResult downOrUp(WmNewsDto dto);

    /**
     * 管理端文章列表查询
     * @param dto
     * @return
     */
    ResponseResult findList(NewsAuthDto dto);

    /**
     * 查询文章详情
     * @param id
     * @return
     */
    ResponseResult findWmNewsVo(Integer id);

    /**
     * 审核文章
     * @param dto
     * @param wmNewsAuth
     * @return
     */
    ResponseResult updateStatus(NewsAuthDto dto, Short wmNewsAuth);
}
