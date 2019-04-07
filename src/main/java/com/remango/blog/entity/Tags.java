package com.remango.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by li on 2019/3/16.
 */

@Data
public class Tags {
    private Long id;
    private String tagsName;
    private Date createTime;
}
