package com.remango.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by li on 2019/3/27.
 */
@Data
public class MessageBoard {
    private Long id;
    private String nickname;
    private String message;
    private Date createTime;
}
