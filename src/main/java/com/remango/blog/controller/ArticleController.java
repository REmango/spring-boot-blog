package com.remango.blog.controller;

import com.remango.blog.aop.ControllerLog;
import com.remango.blog.entity.Article;
import com.remango.blog.entity.ImageResponse;
import com.remango.blog.entity.PageList;
import com.remango.blog.service.ArticleService;
import com.remango.blog.util.FileUtil;
import com.remango.blog.util.TimeUtil;
import com.remango.blog.vo.ResultBean;
import com.remango.blog.vo.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by li on 2019/3/14.
 */
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @ControllerLog("查询文章")
    @GetMapping("/queryArticles")
    public ResultBean getArticle(String key, int offset, int limit) {
        if (offset <= 0) {
            offset = 1;
        }
        if (limit <= 0) {
            limit = 10;
        }
        PageList<Article> PageArticleList = articleService.queryArticleList(key, offset, limit);
        return new ResultBean(PageArticleList);
    }

    @ResponseBody
    @ControllerLog("查询文章")
    @GetMapping("/queryArticleListByTimeLine")
    public ResultBean queryArticleListByTimeLine(String timeLine, int offset, int limit) {
        if (offset <= 0) {
            offset = 1;
        }
        if (limit <= 0) {
            limit = 10;
        }
        PageList<Article> PageArticleList = articleService.queryArticleListByTimeLine(timeLine, offset, limit);
        return new ResultBean(PageArticleList);
    }

    @ResponseBody
    @ControllerLog("查询热点")
    @GetMapping("/queryArticleListByVisits")
    public ResultBean queryArticleListByVisits( int offset, int limit) {
        if (offset <= 0) {
            offset = 1;
        }
        if (limit <= 0) {
            limit = 10;
        }
        PageList<Article> PageArticleList = articleService.queryArticleListByVisits(offset, limit);
        return new ResultBean(PageArticleList);
    }


    @ControllerLog("发布文章")
    @PostMapping("/publishArticle")
    public ResultBean publishArticle(HttpServletRequest request, Article article) throws IOException{

        request.setCharacterEncoding("utf-8");

        articleService.saveArticle(article);

        return new ResultBean(ErrorCode.OK);
    }

    @ControllerLog("查询文章内容")
    @GetMapping("/queryArticleByIdAuthor")
    public ResultBean queryArticleByIdAuthor (Long articleId, String author) {
        Article article = articleService.queryArticle(articleId, author);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("articleId", articleId);
        map.put("author", author);
        articleService.updateArticleVisits(map);
        return new ResultBean(article);
    }

    @GetMapping("/queryArticleInfoById")
    public ResultBean queryArticleInfoById (Long id) {
        Article article = articleService.queryArticleInfoById(id);
        return new ResultBean(article);
    }

    @PostMapping("/uploadImage")
    @ResponseBody
    public ImageResponse uploadImage(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(value = "editormd-image-file", required = false) MultipartFile file){
        ImageResponse imageResponse = new ImageResponse();
        try {
            request.setCharacterEncoding( "utf-8" );
            //设置返回头后页面才能获取返回url
            response.setHeader("Content-Type", "text/html");

            FileUtil fileUtil = new FileUtil();
            String filePath = this.getClass().getResource("/").getPath().substring(1) + "blogImg/";
            String fileContentType = file.getContentType();
            String fileExtension = fileContentType.substring(fileContentType.indexOf("/") + 1);
            TimeUtil timeUtil = new TimeUtil();
            String fileName = timeUtil.getLongTime() + "." + fileExtension;

            String subCatalog = "blogArticles/" + new TimeUtil().getFormatDateForThree();
            String fileUrl = fileUtil.uploadFile(fileUtil.multipartFileToFile(file, filePath, fileName), subCatalog);

            imageResponse.setSuccess(1);
            imageResponse.setMessage("上传成功");
            imageResponse.setUrl(fileUrl);
        } catch (Exception e) {
            try {
                response.getWriter().write( "{\"success\":0}" );
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return imageResponse;
    }
}
