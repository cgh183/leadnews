package com.heima.behavior.controller.v1;
 
import com.heima.behavior.service.UnlikesBehaviorService;
import com.heima.model.behavior.dtos.UnLikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/un_likes_behavior")
public class UnlikesBehaviorController {
 
    @Autowired
    private UnlikesBehaviorService unlikesBehaviorService;

    /**
     * 用户不喜欢与取消
     * @param dto
     * @return
     */
    @PostMapping
    public ResponseResult unLike(@RequestBody UnLikesBehaviorDto dto) {
        return unlikesBehaviorService.unLike(dto);
    }
}