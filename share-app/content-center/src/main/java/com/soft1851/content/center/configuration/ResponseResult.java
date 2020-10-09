package com.soft1851.content.center.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author su
 * @className ResponseResult
 * @Description TODO
 * @Date 2020/9/29
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult implements Serializable {

    private static final long serialVersionUID = -3948389268046368059L;

    private Integer code;

    private String msg;

    private Object data;


    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
