package com.remango.blog.controller;

import com.google.code.kaptcha.Constants;
import com.remango.blog.aop.ControllerLog;
import com.remango.blog.entity.CurrentUserInfo;
import com.remango.blog.entity.User;
import com.remango.blog.service.UserService;
import com.remango.blog.vo.ErrorCode;
import com.remango.blog.vo.ResultBean;
import com.remango.blog.entity.PageList;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by li on 2019/3/16.
 */

@RestController
public class UserController {

    public static String LOGIN_NAME_KEY = "LOGIN_NAME";

    @Autowired
    private UserService userService;


    @ResponseBody
    @ControllerLog("查询用户")
    @GetMapping("/queryUser")
    public ResultBean getArticle(String username, int offset, int limit) {
        PageList<User> PageUserList = userService.queryUserList(username, offset, limit);
        return new ResultBean(PageUserList);
    }


    @ResponseBody
    @ControllerLog("查询用户")
    @GetMapping("/queryUserName")
    public ResultBean queryUserName(String username) {
        User user = userService.queryUserByName(username);

        return new ResultBean(user);
    }

    @ControllerLog("添加用户")
    @PostMapping("/saveUser")
    public ResultBean saveUser(User user) {
        userService.saveUser(user);
        return new ResultBean(ErrorCode.OK);
    }

    @PostMapping("/login")
    @ControllerLog
    public ResultBean login(String username, String password, HttpSession session) {
        Long verifyTime = (Long) session.getAttribute(Constants.KAPTCHA_SESSION_DATE);
        session.removeAttribute(Constants.KAPTCHA_SESSION_DATE);
        if (null == verifyTime || System.currentTimeMillis() - verifyTime > 5*60*1000) {
            return new ResultBean(ErrorCode.CAPTCHA_VERIFY_FAILED);
        }

        SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));

        session.setAttribute(LOGIN_NAME_KEY, username);

        return new ResultBean(ErrorCode.OK);
    }



    @PostMapping("/logout")
    @ControllerLog
    public ResultBean logout(HttpSession session) {
        session.removeAttribute(LOGIN_NAME_KEY);

        return new ResultBean(ErrorCode.OK);
    }

    @GetMapping("/getCurrentUser")
    @ControllerLog
    public ResultBean getCurrentUser(HttpSession session) {
        String loginName = (String) session.getAttribute(LOGIN_NAME_KEY);
        CurrentUserInfo currentUserInfo = userService.queryUserInfoByName(loginName);

        return new ResultBean(currentUserInfo);
    }

}
