package com.remango.blog.shiro;


import com.remango.blog.entity.User;
import com.remango.blog.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;

/**
 * Created by li on 2019/3/16.
 */
public class MyRealm extends AuthenticatingRealm {

    @Resource
    @Lazy
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        User user = userService.queryUserByName(username);

        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        String password = user.getPassword();

        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
