package com.remango.blog.controller;

import com.remango.blog.entity.PageList;
import com.remango.blog.entity.Timeline;
import com.remango.blog.service.TimelineService;
import com.remango.blog.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by li on 2019/3/16.
 */
@RestController
public class TimelineController {
    @Autowired
    private TimelineService timelineService;
    @ResponseBody
    @GetMapping("/getTimeline")
    public ResultBean getTags(@RequestParam(name = "offset",required = true) int offset, @RequestParam(name = "limit",required = true) int limit) {
        PageList<Timeline> PageLogList = timelineService.queryTimelineList(offset, limit);
        return new ResultBean(PageLogList);
    }

}
