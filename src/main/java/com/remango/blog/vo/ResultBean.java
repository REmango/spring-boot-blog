package com.remango.blog.vo;

import lombok.Data;

/**
 * @author zhengli
 * @since 2019/03/07
 */


@Data
public class ResultBean<T> {

    private int errorCode;
    private String errorMsg;

    private T data;
    public ResultBean(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.errorMsg = errorCode.getMessage();
    }
    public ResultBean(T data) {
        this(ErrorCode.OK);
        this.data = data;
    }
}
