package com.remango.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zhengli
 * @since 2019/03/07
 */

@Data
public class Log {
    private Long id;
    private String ip;
    private String operation;
    private String method;
    private String params;
    private Integer spendTime;
    private Date createTime;

    public Log() {
    }

}
