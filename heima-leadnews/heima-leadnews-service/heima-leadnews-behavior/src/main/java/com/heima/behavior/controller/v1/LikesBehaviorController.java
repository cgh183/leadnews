package com.heima.behavior.controller.v1;
 
import com.heima.behavior.service.LikesBehaviorService;
import com.heima.model.behavior.dtos.LikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 

@RestController
@RequestMapping("/api/v1/likes_behavior")
public class LikesBehaviorController {
 
    @Autowired
    private LikesBehaviorService likesBehaviorService;

    /**
     * 用户点赞与取消
     * @param dto
     * @return
     */
    @PostMapping
    public ResponseResult like(@RequestBody LikesBehaviorDto dto) {
        return likesBehaviorService.like(dto);
    }
}