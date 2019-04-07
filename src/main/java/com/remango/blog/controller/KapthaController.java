package com.remango.blog.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.remango.blog.aop.ControllerLog;
import com.remango.blog.vo.ResultBean;
import com.remango.blog.vo.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by li on 2019/3/16.
 */

@Controller
@RequestMapping("/captcha")
@Slf4j
public class KapthaController {
    @Autowired
    public Producer producer;
    @GetMapping("/get")
    @ControllerLog("获取验证码")
    public void get(HttpSession session, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        log.info("captcha:{}", text);
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }
    @PostMapping("/verify")
    @ResponseBody
    @ControllerLog
    public ResultBean verify(String captcha, HttpSession session) {
        String sessionCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!StringUtils.hasLength(sessionCaptcha)) {
            return new ResultBean(ErrorCode.NOT_GET_CAPTCHA);
        }

        if (!sessionCaptcha.equalsIgnoreCase(captcha)) {
            return new ResultBean(ErrorCode.CAPTCHA_VERIFY_FAILED);
        }
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        session.setAttribute(Constants.KAPTCHA_SESSION_DATE, System.currentTimeMillis());
        return new ResultBean(ErrorCode.OK);
    }
}
