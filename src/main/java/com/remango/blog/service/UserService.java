package com.remango.blog.service;

import com.remango.blog.entity.CurrentUserInfo;
import com.remango.blog.entity.User;
import com.remango.blog.entity.PageList;

/**
 * Created by li on 2019/3/16.
 */
public interface UserService {

    /**
     * 根据用户名模糊查询用户
     * @param username 用户名
     * @param offset 偏移量
     * @param limit 条数
     * @return 用户
     */
    PageList<User> queryUserList(String username, Integer offset, Integer limit);

    /**
     * 保存用户
     * @param user 用户对象
     */
    void saveUser(User user);

    /**
     * 根据用户名查询当前用户，包含密码
     * @param username 用户名
     */
    User queryUserByName(String username);

    /**
     * 根据用户名查询当前用户，不包含密码
     * @param username 用户名
     */
    CurrentUserInfo queryUserInfoByName (String username);
}
