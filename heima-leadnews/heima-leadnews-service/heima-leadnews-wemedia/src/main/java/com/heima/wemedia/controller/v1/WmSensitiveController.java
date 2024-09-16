package com.heima.wemedia.controller.v1;
 
import com.heima.model.admin.dtos.SensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.wemedia.service.WmSensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 

@RestController
@RequestMapping("/api/v1/sensitive")
public class WmSensitiveController {
 
    @Autowired
    private WmSensitiveService wmSensitiveService;
 
    @PostMapping("/list")
    public ResponseResult list(@RequestBody SensitiveDto dto) {
        return wmSensitiveService.findList(dto);
    }
 
    @DeleteMapping("/del/{id}")
    public ResponseResult del(@PathVariable("id") Integer id) {
        return wmSensitiveService.delSensitive(id);
    }
 
    @PostMapping("/save")
    public ResponseResult save(@RequestBody AdSensitive adSensitive) {
        return wmSensitiveService.saveSensitive(adSensitive);
    }
 
    @PostMapping("/update")
    public ResponseResult update(@RequestBody AdSensitive adSensitive) {
        return wmSensitiveService.updateSensitive(adSensitive);
    }
}