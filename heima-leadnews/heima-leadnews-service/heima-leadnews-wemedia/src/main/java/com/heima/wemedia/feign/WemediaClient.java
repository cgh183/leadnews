package com.heima.wemedia.feign;
 
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.apis.wemedia.IWemediaClient;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.wemedia.service.WmChannelService;
import com.heima.wemedia.service.WmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 

@RestController
public class WemediaClient implements IWemediaClient {
 
    @Autowired
    private WmUserService wmUserService;

    @Autowired
    private WmChannelService wmChannelService;
 
    /**
     * 根据名称查询自媒体用户信息
     * @param name
     * @return
     */
    @GetMapping("/api/v1/user/findByName/{name}")
    public ResponseResult findWmUserByName(@PathVariable("name") String name) {
        return wmUserService.findWmUserByName(name);
    }
 
    /**
     * 创建自媒体用户
     * @param wmUser
     */
    @Override
    @PostMapping("/api/v1/wm_user/save")
    public ResponseResult saveWmUser(@RequestBody WmUser wmUser) {
        wmUserService.saveWmUser(wmUser);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    @GetMapping("/api/v1/channel/list")
    public ResponseResult getChannels() {
        return wmChannelService.findAll();
    }
}