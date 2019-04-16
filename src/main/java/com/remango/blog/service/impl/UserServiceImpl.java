package com.remango.blog.service.impl;

import com.remango.blog.dao.UserDao;
import com.remango.blog.entity.CurrentUserInfo;
import com.remango.blog.entity.PageList;
import com.remango.blog.entity.User;
import com.remango.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by li on 2019/3/16.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public PageList<User> queryUserList(String username, Integer offset, Integer limit){
        PageList<User> userPageList = new PageList<>();
        List<User> userList= userDao.queryUserList(username, offset, limit);
        int total = userDao.queryUserTotal(username);
        userPageList.setRows(userList);
        userPageList.setTotal(total);
        return userPageList;
    }

    @Override
    public void saveUser(User user){
        user.setCreateTime(new Date());
        userDao.saveUser(user);
    }

    @Override
    public User queryUserByName(String username) {
        return userDao.queryUserByName(username);
    }

    @Override
    public CurrentUserInfo queryUserInfoByName (String username){
        return userDao.queryUserInfoByName(username);
    }
    
}
