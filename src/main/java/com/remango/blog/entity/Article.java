package com.remango.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by li on 2019/3/14.
 */
@Data
public class Article {
    private Long id;
    private Long articleId;
    private String author;
    private String articleTitle;
    private String type;
    private String summary;
    private String categories;
    private String content;
    private Integer likes;
    private Long lastArticleId;
    private String nextArticleId;
    private String timeline;
    private Date createTime;
    private Date updateTime;
    private Integer visits;
}
