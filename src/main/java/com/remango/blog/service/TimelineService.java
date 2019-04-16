package com.remango.blog.service;

import com.remango.blog.entity.PageList;
import com.remango.blog.entity.Timeline;

import java.util.List;

/**
 * Created by li on 2019/3/16.
 */
public interface TimelineService {

    /**
     * 查询所有的时间轴
     * @param offset 偏移量
     * @param limit 条数
     * @return 文章列表
     */
    PageList<Timeline> queryTimelineList(Integer offset, Integer limit);

    /**
     * 保存新的时间轴
     * @param timeline 时间轴对象
     */
    void saveTimeline(Timeline timeline);

    /**
     * 通过name查询所有时间轴
     * @param name 名称
     */
    List<Timeline> queryTimelineListByName(String name);
}
