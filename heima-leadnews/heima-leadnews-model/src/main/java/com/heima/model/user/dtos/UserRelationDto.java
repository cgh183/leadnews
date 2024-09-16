package com.heima.model.user.dtos;
 
import com.heima.model.common.annotation.IdEncrypt;
import lombok.Data;
 

@Data
public class UserRelationDto {
 
    /*
     * 文章id
     */
    @IdEncrypt
    private Long article;
 
    /*
     * 作者id
     */
    @IdEncrypt
    private Integer authorId;
 
    /*
     * 0:关注 1:取消
     */
    private Short operation;
}