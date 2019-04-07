package com.remango.blog.entity;

import lombok.Data;

/**
 * Created by li on 2019/3/30.
 */
@Data
public class ImageResponse {
    private int success;
    private String message;
    private  String url;
}
