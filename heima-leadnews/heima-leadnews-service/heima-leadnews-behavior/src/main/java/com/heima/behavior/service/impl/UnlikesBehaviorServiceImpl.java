package com.heima.behavior.service.impl;
 
import com.alibaba.fastjson.JSON;
import com.heima.common.constants.BehaviorConstants;
import com.heima.common.redis.CacheService;
import com.heima.behavior.service.UnlikesBehaviorService;
import com.heima.model.behavior.dtos.UnLikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.common.threadLocal.AppThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 

@Service
@Slf4j
public class UnlikesBehaviorServiceImpl implements UnlikesBehaviorService {
 
    @Autowired
    private CacheService cacheService;
 
    /**
     * 不喜欢
     * @param dto
     * @return
     */
    @Override
    public ResponseResult unLike(UnLikesBehaviorDto dto) {
 
        //校验参数
        if (dto.getArticleId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
 
        //判断是否登录
        ApUser user = AppThreadLocalUtil.getUser();
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
 
        //设置不喜欢与取消不喜欢
        if (dto.getType() == 0) {
            log.info("保存当前key:{}, {}, {}", dto.getArticleId(), user.getId(), dto);
            cacheService.hPut(BehaviorConstants.UN_LIKE_BEHAVIOR + dto.getArticleId().toString(), user.getId().toString(), JSON.toJSONString(dto));
        } else {
            log.info("删除当前key:{}, {}, {}", dto.getArticleId(), user.getId(), dto);
            cacheService.hDelete(BehaviorConstants.UN_LIKE_BEHAVIOR + dto.getArticleId().toString(), user.getId().toString());
        }
 
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}