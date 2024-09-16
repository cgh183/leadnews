package com.heima.model.admin.dtos;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class AdUserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 登录用户名
     */
    @ApiModelProperty(value = "用户名",required = true)
    private String name;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "手机号",required = true)
    private String password;
}
