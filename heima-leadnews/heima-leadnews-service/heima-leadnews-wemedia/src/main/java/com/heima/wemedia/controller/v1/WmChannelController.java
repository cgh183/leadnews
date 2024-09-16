package com.heima.wemedia.controller.v1;

import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.wemedia.service.WmChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/channel")
public class WmChannelController {

    @Autowired
    private WmChannelService wmChannelService;

    @GetMapping("/channels")
    public ResponseResult findAll() {
        return wmChannelService.findAll();
    }

    @PostMapping("/list")
    public ResponseResult list(@RequestBody ChannelDto dto) {
        return wmChannelService.findList(dto);
    }

    @PostMapping("/save")
    public ResponseResult save(@RequestBody AdChannel adChannel) {
        return wmChannelService.saveChannel(adChannel);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody AdChannel adChannel) {
        return wmChannelService.updateChannel(adChannel);
    }

    @GetMapping("/del/{id}")
    public ResponseResult del(@PathVariable("id") Integer id) {
        return wmChannelService.deleteChannel(id);
    }
}
