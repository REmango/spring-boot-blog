package com.remango.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by li on 2019/3/16.
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String gender;
    private String email;
    private String avatarUrl;
    private String personalProfile;
    private Date createTime;
    private String lastLoginTime;
}
