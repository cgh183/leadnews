package com.heima.model.admin.dtos;
 
import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;
 

@Data
public class SensitiveDto extends PageRequestDto {

    private String name;

}