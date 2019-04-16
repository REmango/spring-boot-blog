package com.remango.blog.dao;

import com.remango.blog.entity.Tags;

import java.util.List;

/**
 * Created by li on 2019/3/16.
 */
public interface TagsDao {

    void saveTags(Tags tags);

    List<Tags> queryTagsList();

    int queryTagsTotal();
}
