package com.heima.wemedia.service;


import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmNews;

public interface WmNewsAutoScanService {

    /**
     * 自媒体文章审核
     * @param id 自媒体文章id
     */
    public void autoScanWmNews(Integer id);


    ResponseResult saveAppArticle(WmNews wmNews);
}
