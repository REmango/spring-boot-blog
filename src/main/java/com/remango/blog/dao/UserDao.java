package com.remango.blog.dao;

import com.remango.blog.entity.CurrentUserInfo;
import com.remango.blog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by li on 2019/3/16.
 */
public interface UserDao {

    void saveUser(User user);

    List<User> queryUserList(@Param("username") String username, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int queryUserTotal(@Param("username") String username);

    User queryUserByName(@Param("username") String username);

    CurrentUserInfo queryUserInfoByName (@Param("username") String username);
}
