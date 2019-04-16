package com.remango.blog.dao;

import com.remango.blog.entity.Timeline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by li on 2019/3/16.
 */
public interface TimelineDao {

    void saveTimeline(Timeline timeline);

    List<Timeline> queryTimelineList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    List<Timeline> queryTimelineListByName(@Param("name") String name);

    int queryTimelineTotal();
}
