package com.remango.blog.dao;

import com.remango.blog.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhengli
 * @since 2019/03/07
 */

public interface LogDao {

    void saveLog(Log log);

    List<Log> queryLogList(@Param("key") String key, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int queryLogTotal(@Param("key") String key);
}
