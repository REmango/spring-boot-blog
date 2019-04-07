package com.remango.blog.service;

import com.remango.blog.entity.CurrentUserInfo;
import com.remango.blog.entity.User;
import com.remango.blog.entity.PageList;

/**
 * Created by li on 2019/3/16.
 */
public interface UserService {
    PageList<User> queryUserList(String username, Integer offset, Integer limit);
    void saveUser(User user);
    User queryUserByName(String username);
    CurrentUserInfo queryUserInfoByName (String username);
}
