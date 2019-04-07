package com.remango.blog.controller;

import com.remango.blog.aop.ControllerLog;
import com.remango.blog.entity.PageList;
import com.remango.blog.entity.Tags;
import com.remango.blog.service.TagsService;
import com.remango.blog.vo.ResultBean;
import com.remango.blog.vo.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Created by li on 2019/3/16.
 */
@RestController
public class TagsController {
    @Autowired
    private TagsService tagsService;
    @ResponseBody
    @GetMapping("/getTags")
    public ResultBean getTags() {
        PageList<Tags> PageLogList = tagsService.queryTagsList();
        return new ResultBean(PageLogList);
    }
    @ControllerLog("保存标签")
    @PostMapping("/saveTags")
    public ResultBean saveTags(HttpServletRequest request, Tags tags) throws IOException {
        request.setCharacterEncoding("utf-8");
        tagsService.saveTags(tags);
        return new ResultBean(ErrorCode.OK);
    }

}
