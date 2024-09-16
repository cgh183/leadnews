package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.heima.apis.wemedia.IWemediaClient;
import com.heima.common.constants.UserConstants;
import com.heima.model.admin.dtos.AuthDto;
import com.heima.model.admin.pojos.ApUserRealname;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.mapper.ApUserRealnameMapper;
import com.heima.user.service.ApUserRealnameService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
@Transactional
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {

    @Autowired
    private IWemediaClient wemediaClient;

    @Autowired
    private ApUserMapper apUserMapper;

    /**
     * 用户审核列表 - 根据状态分页查询
     * @param dto
     * @return
     */
    @Override
    public ResponseResult list(AuthDto dto) {
 
        //校验参数
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        dto.checkParam();
 
        //分页查询
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<ApUserRealname> queryWrapper = new LambdaQueryWrapper<>();
 
        //根据状态查询
        if (dto.getStatus() != null) {
            queryWrapper.eq(ApUserRealname::getStatus, dto.getStatus());
        }
        page = page(page, queryWrapper);
 
        //返回结果
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }

    /**
     * 更新认证状态
      * @param dto
     * @param status
     * @return
     */
    @Override
    public ResponseResult updateStatus(AuthDto dto, Short status) {
        //校验参数
        if (dto == null || dto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //修改认证状态
        ApUserRealname apUserRealname = new ApUserRealname();
        apUserRealname.setId(dto.getId());
        apUserRealname.setStatus(status);
        apUserRealname.setUpdatedTime(new Date());
        if(StringUtils.isNotBlank(dto.getMsg())){
            apUserRealname.setReason(dto.getMsg());
        }
        updateById(apUserRealname);

        //如果认证成功，则创建账户
        if (status.equals(UserConstants.PASS_AUTH)) {
            ResponseResult responseResult = createWmUserAndAuthor(dto);
            return responseResult;
        }

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 创建自媒体帐号
     * @param dto
     * @return
     */
    private ResponseResult createWmUserAndAuthor(AuthDto dto) {
        Integer userRealnameId = dto.getId();
        //查询用户认证信息
        ApUserRealname apUserRealname = getById(userRealnameId);
        if(apUserRealname == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //查询app端用户信息
        Integer userId = apUserRealname.getUserId();
        ApUser apUser = apUserMapper.selectById(userId);
        if(apUser == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //创建自媒体账户
        ResponseResult responseResult = wemediaClient.findWmUserByName(apUser.getName());
        if(responseResult.getCode().equals(AppHttpCodeEnum.SUCCESS.getCode()) && responseResult.getData() == null){
            WmUser wmUser= new WmUser();
            wmUser.setApUserId(apUser.getId());
            wmUser.setCreatedTime(new Date());
            wmUser.setName(apUser.getName());
            wmUser.setPassword(apUser.getPassword());
            wmUser.setSalt(apUser.getSalt());
            wmUser.setPhone(apUser.getPhone());
            wmUser.setStatus(9);
            wemediaClient.saveWmUser(wmUser);
        }
        apUser.setFlag((short)1);
        apUserMapper.updateById(apUser);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}