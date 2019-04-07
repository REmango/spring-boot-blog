package com.remango.blog.aop;

import com.remango.blog.vo.ErrorCode;
import com.remango.blog.vo.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by li on 2019/3/23.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UnknownAccountException.class)
    @ResponseBody
    public ResultBean handleUnknownAccountException(HttpServletRequest request, UnknownAccountException e) {
        log.warn("Unknown Account Exception", e);

        return new ResultBean(ErrorCode.USER_NO_EXISTS);
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseBody
    public ResultBean handleUnknownAccountException(HttpServletRequest request, IncorrectCredentialsException e) {
        log.warn("Incorrect Credential Exception", e);

        return new ResultBean(ErrorCode.PASSWORD_ERROR);
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultBean handle(HttpServletRequest request, Exception e) {
        log.error("Other Exception", e);

        return new ResultBean(ErrorCode.UNKNOWN_REASON);
    }

}
