package com.remango.blog.service.impl;

import com.remango.blog.dao.TagsDao;
import com.remango.blog.entity.PageList;
import com.remango.blog.entity.Tags;
import com.remango.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by li on 2019/3/16.
 */
@Service
public class TagsServiceImpl implements TagsService{
    @Autowired
    private TagsDao tagsDao;

    @Override
    public PageList<Tags> queryTagsList(){
        PageList<Tags> tagsPageList = new PageList<>();

        List<Tags> tagsList= tagsDao.queryTagsList();
        int total = tagsDao.queryTagsTotal();

        tagsPageList.setRows(tagsList);
        tagsPageList.setTotal(total);
        return tagsPageList;
    }

    @Override
    public void saveTags(Tags tags){
        tags.setCreateTime(new Date());
        tagsDao.saveTags(tags);
    }
}
