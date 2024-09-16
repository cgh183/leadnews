package com.heima.behavior.controller.v1;
 
import com.heima.behavior.service.ReadBehaviorService;
import com.heima.model.behavior.dtos.ReadBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 

@RestController
@RequestMapping("/api/v1/read_behavior")
public class ReadBehaviorController {
 
    @Autowired
    private ReadBehaviorService readBehaviorService;


    /**
     * 阅读行为
     * @param dto
     * @return
     */
    @PostMapping
    public ResponseResult readBehavior(@RequestBody ReadBehaviorDto dto) {
        return readBehaviorService.readBehavior(dto);
    }
}