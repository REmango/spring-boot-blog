package com.remango.blog.controller;

import com.remango.blog.aop.ControllerLog;
import com.remango.blog.entity.Log;
import com.remango.blog.entity.PageList;
import com.remango.blog.service.LogService;
import com.remango.blog.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengli
 * @since 2019/03/07
 */

@RestController
public class LogController {
    @Autowired
    private LogService logService;
    @ResponseBody
    @GetMapping("/getLog")
    public ResultBean getLog(String key, int offset, int limit) {
        PageList<Log> PageLogList = logService.queryList(key, offset, limit);
        return new ResultBean(PageLogList);
    }
}
