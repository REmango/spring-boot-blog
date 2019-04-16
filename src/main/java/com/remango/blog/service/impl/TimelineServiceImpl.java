package com.remango.blog.service.impl;

import com.remango.blog.dao.TimelineDao;
import com.remango.blog.entity.PageList;
import com.remango.blog.entity.Timeline;
import com.remango.blog.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by li on 2019/3/16.
 */
@Service
public class TimelineServiceImpl implements TimelineService {
    @Autowired
    private TimelineDao timelineDao;

    @Override
    public PageList<Timeline> queryTimelineList(Integer offset, Integer limit){
        PageList<Timeline> timelinePageList = new PageList<>();

        List<Timeline> timelineList= timelineDao.queryTimelineList(offset, limit);
        int total = timelineDao.queryTimelineTotal();

        timelinePageList.setRows(timelineList);
        timelinePageList.setTotal(total);
        return timelinePageList;
    }

    @Override
    public void saveTimeline(Timeline timeline){
        timelineDao.saveTimeline(timeline);
    }

    @Override
    public List<Timeline> queryTimelineListByName(String name){
        return timelineDao.queryTimelineListByName(name);
    }
}
