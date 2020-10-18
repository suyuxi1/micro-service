package com.soft1851.content.center.domain.dto;

import com.soft1851.content.center.domain.entity.Share;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author su
 * @className ShareDto
 * @Description TODO
 * @Date 2020/9/29
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareDTO {

    private Share share;

    private String wxNickname;

}
