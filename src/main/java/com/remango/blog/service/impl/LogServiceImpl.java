package com.remango.blog.service.impl;

import com.remango.blog.dao.LogDao;
import com.remango.blog.entity.Log;
import com.remango.blog.entity.PageList;
import com.remango.blog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/3/8.
 */

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    @Override
    public PageList<Log> queryList(String key,  Integer offset, Integer limit) {
        PageList<Log> logPageList = new PageList<>();
        List<Log> userList= logDao.queryLogList(key, offset, limit);
        int total = logDao.queryLogTotal(key);
        logPageList.setRows(userList);
        logPageList.setTotal(total);
        return logPageList;
    }


    @Override
    @Transactional
    public void saveLog(Log log) {
        log.setCreateTime(new Date());
        logDao.saveLog(log);
    }
}
