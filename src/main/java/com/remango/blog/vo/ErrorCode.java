package com.remango.blog.vo;

/**
 * @author zhengli
 * @since 2019/03/07
 */

public enum ErrorCode {
    OK(0, "成功"),
    NOT_LOGIN(1004, "未登录"),
    USER_NO_EXISTS(1005, "用户名不存在"),
    PASSWORD_ERROR(1006, "密码错误"),
    NOT_GET_CAPTCHA(1007, "请先获取校验码"),
    CAPTCHA_VERIFY_FAILED(1008, "校验码未校验通过"),
    UNKNOWN_REASON(2000, "未知错误，请联系管理员")
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
