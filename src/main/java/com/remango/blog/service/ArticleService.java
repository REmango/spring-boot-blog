package com.remango.blog.service;

import com.remango.blog.entity.Article;
import com.remango.blog.entity.PageList;

import java.util.Map;

/**
 * Created by li on 2019/3/14.
 */
public interface ArticleService {

    /**
     * 根据模糊字段查询
     * @param key 字段
     * @param offset 偏移量
     * @param limit 条数
     * @return 文章列表
     */
    PageList<Article> queryArticleList(String key, Integer offset, Integer limit);

    /**
     * 根据时间轴字段查询
     * @param timeLine 时间轴
     * @param offset 偏移量
     * @param limit 条数
     * @return 文章列表
     */
    PageList<Article> queryArticleListByTimeLine(String timeLine, Integer offset, Integer limit);

    /**
     * 按访问量排序
     * @param offset 偏移量
     * @param limit 条数
     * @return 文章列表
     */
    PageList<Article> queryArticleListByVisits(Integer offset, Integer limit);


    /**
     * 保存文章
     * @param article 文章对象
     */
    void saveArticle(Article article);

    /**
     * 通过作者和id查询文章
     * @param articleId 文章id
     * @param author  作者
     * @return 文章列表
     */
    Article queryArticle(Long articleId, String author);

    /**
     * 更新文章的访问量
     */
    void updateArticleVisits(Map<String, Object> map);

    /**
     * 通过id查询文章简要信息，不包含文章内容
     * @param id id
     * @return 文章列表
     */
    Article queryArticleInfoById(Long id);
}
