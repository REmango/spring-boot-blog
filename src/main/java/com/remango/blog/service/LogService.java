package com.remango.blog.service;

import com.remango.blog.entity.Log;
import com.remango.blog.entity.PageList;


/**
 * Created by lenovo on 2019/3/8.
 */
public interface LogService {

    PageList<Log> queryList(String key,  Integer offset, Integer limit);
    void saveLog(Log log);
}
