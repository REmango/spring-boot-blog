package com.remango.blog.service;

import com.remango.blog.entity.PageList;
import com.remango.blog.entity.Timeline;

import java.util.List;

/**
 * Created by li on 2019/3/16.
 */
public interface TimelineService {
    PageList<Timeline> queryTimelineList(Integer offset, Integer limit);
    void saveTimeline(Timeline timeline);
    List<Timeline> queryTimelineListByName(String name);
}
