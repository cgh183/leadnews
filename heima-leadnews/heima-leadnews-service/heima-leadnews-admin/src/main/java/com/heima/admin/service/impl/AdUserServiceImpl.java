package com.heima.admin.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.AdUserMapper;
import com.heima.admin.service.AdUserService;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.admin.pojos.AdUser;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AdUserServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements AdUserService {


    /**
     * admin端登录功能
     * @param dto
     * @return
     */
    @Override
    public ResponseResult login(AdUserDto dto) {

        //检查参数
        if (StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getPassword())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"用户名或密码为空");
        }

        //查询用户
        AdUser user = getOne(Wrappers.<AdUser>lambdaQuery().eq(AdUser::getName, dto.getName()));
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "用户不存在");
        }

        //对比密码
        String salt = user.getSalt();
        String pswd = dto.getPassword();
        pswd = DigestUtils.md5DigestAsHex((pswd + salt).getBytes());
        if (pswd.equals(user.getPassword())) {
            //返回数据 jwt
            Map<String,Object> map = new HashMap<>();
            map.put("token", AppJwtUtil.getToken(user.getId().longValue()));
            user.setSalt("");
            user.setPassword("");
            map.put("user",user);
            return ResponseResult.okResult(map);
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
    }
}
