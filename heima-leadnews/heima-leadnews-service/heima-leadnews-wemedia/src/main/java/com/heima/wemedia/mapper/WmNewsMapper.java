package com.heima.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.admin.dtos.NewsAuthDto;
import com.heima.model.wemedia.vos.WmNewsVo;
import com.heima.model.wemedia.pojos.WmNews;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface WmNewsMapper extends BaseMapper<WmNews> {

    /**
     * 文章分页查询
     * @param dto
     * @return
     */
    List<WmNewsVo> findListAndPage(NewsAuthDto dto);

    /**
     * 查询记录数
     * @param dto
     * @return
     */
    int findListCount(NewsAuthDto dto);
}
