package com.remango.blog.service;

import com.remango.blog.entity.Log;
import com.remango.blog.entity.PageList;


/**
 * Created by lenovo on 2019/3/8.
 */
public interface LogService {

    /**
     * 根据模糊字段查询
     * @param key 字段
     * @param offset 偏移量
     * @param limit 条数
     * @return 日志列表
     */
    PageList<Log> queryList(String key,  Integer offset, Integer limit);

    /**
     * 保存日志
     * @param log 日志对象
     */
    void saveLog(Log log);
}
