package com.remango.blog.shiro;

import com.alibaba.fastjson.JSON;
import com.remango.blog.vo.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import com.remango.blog.vo.ResultBean;
import com.remango.blog.controller.UserController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by li on 2019/3/16.
 */

@Slf4j
public class MyRequestFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        String url = ((ShiroHttpServletRequest) servletRequest).getRequestURI();
        if (url.contains("swagger") || url.contains("api-docs") || url.contains(".png") ||
                url.contains(".html") || url.contains(".js") || url.contains(".css") ||
                url.contains(".ttf") || url.contains(".eot") || url.contains(".woff") ||
                url.contains(".apk") || url.contains(".crx") || url.contains(".ipa") ||
                url.contains("login") || url.contains("logout") || url.contains("captcha") || url.contains(".ico") ||
                "/blog/".equals(url) || url.contains("queryArticles")|| url.contains("queryArticleByIdAuthor") ||
                url.contains("getTags") || url.contains("getTimeline") || url.contains("getCurrentUser") ||
                url.contains("queryArticleListByTimeLine") || url.contains("queryArticleInfoById")||
                url.contains("uploadImage") || url.contains("getMessageBoard") ||
                url.contains("saveMessageBoard") || url.contains("queryArticleListByVisits")) {

            return true;
        }

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpSession httpSession = ((ShiroHttpServletRequest) servletRequest).getSession();

        String loginName = (String) httpSession.getAttribute(UserController.LOGIN_NAME_KEY);
        if (!StringUtils.hasLength(loginName)) {
            ResultBean resultBean = new ResultBean(ErrorCode.NOT_LOGIN);
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultBean));

            log.info("Not Login");
            return false;
        }

        return true;
    }
}
