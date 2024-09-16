package com.heima.model.admin.dtos;
 
import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;


@Data
public class NewsAuthDto extends PageRequestDto {
 
    private Integer id;
    private String msg;
    private Integer status;
    private String title;
}