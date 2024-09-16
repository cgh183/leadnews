package com.heima.apis.wemedia;
 
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
 
/**
 * @author Z-熙玉
 * @version 1.0
 */
@FeignClient("leadnews-wemedia")
public interface IWemediaClient {
 
    /**
     * 根据名称查询自媒体用户信息
     * @param name
     * @return
     */
    @GetMapping("/api/v1/user/findByName/{name}")
    ResponseResult findWmUserByName(@PathVariable("name") String name);
 
    /**
     * 创建自媒体用户
     * @param wmUser
     */
    @PostMapping("/api/v1/wm_user/save")
    ResponseResult saveWmUser(@RequestBody WmUser wmUser);

    /**
     * 获取所有频道
     * @return
     */
    @GetMapping("/api/v1/channel/list")
    ResponseResult getChannels();

}