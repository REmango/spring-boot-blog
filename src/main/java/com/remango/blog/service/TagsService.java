package com.remango.blog.service;

import com.remango.blog.entity.PageList;
import com.remango.blog.entity.Tags;

/**
 * Created by li on 2019/3/16.
 */
public interface TagsService {

    PageList<Tags> queryTagsList();

    void saveTags(Tags tags);
}
