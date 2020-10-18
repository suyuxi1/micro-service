package com.soft1851.content.center.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author su
 * @className ShareRequestDTO
 * @Description TODO
 * @Date 2020/10/7
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareRequestDTO {

//    @Column(name = "userId")
//    @ApiModelProperty(name = "userId",value = "用户id")
//    private  Integer userId;


//    @Column(name = "author")
//    @ApiModelProperty(name = "author", value = "资源作者")
    private String author;

//    @Column(name = "download_url")
//    @ApiModelProperty(name = "downloadUrl", value = "下载地址")
    private String downloadUrl;

//    @Column(name = "is_original")
//    @ApiModelProperty(name = "isOriginal", value = "是否原创 0：否 1：是")
    private Boolean isOriginal;


//    @Column(name = "price")
//    @ApiModelProperty(name = "price", value = "下载所需积分")
    private Integer price;

//    @Column(name = "summary")
//    @ApiModelProperty(name = "summary", value = "资源摘要")
    private String summary;

//    @Column(name = "title")
//    @ApiModelProperty(name = "title", value = "分享内容标题")
    private String title;


}
