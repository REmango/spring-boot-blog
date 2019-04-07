package com.remango.blog.service;

import com.remango.blog.entity.Article;
import com.remango.blog.entity.PageList;

import java.util.Map;

/**
 * Created by li on 2019/3/14.
 */
public interface ArticleService {
    PageList<Article> queryArticleList(String key, Integer offset, Integer limit);

    PageList<Article> queryArticleListByTimeLine(String TimeLine, Integer offset, Integer limit);

    PageList<Article> queryArticleListByVisits(Integer offset, Integer limit);

    void saveArticle(Article article);

    Article queryArticle(Long articleId, String author);

    void updateArticleVisits(Map<String, Object> map);

    Article queryArticleInfoById(Long id);
}
