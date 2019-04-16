package com.remango.blog.dao;

import com.remango.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by li on 2019/3/14.
 */
public interface ArticleDao {

    Article queryArticle(@Param("articleId") Long articleId, @Param("author") String author);

    Article queryArticleInfoById(@Param("id") Long id);

    void saveArticle(Article article);

    List<Article> queryArticleListByKey(@Param("key") String key, @Param("offset") Integer offset, @Param("limit") Integer limit);

    List<Article> queryArticleListByTimeLine(@Param("TimeLine") String TimeLine, @Param("offset") Integer offset, @Param("limit") Integer limit);

    List<Article> queryArticleListByVisits(@Param("offset") Integer offset, @Param("limit") Integer limit);

    int queryArticleTotalByTimeLine(@Param("TimeLine") String TimeLine);

    int queryArticleTotalByKey(@Param("key") String key);

    void updateArticleVisits(Map<String, Object> map);

    Article queryPrePageId ();

    void updatePrePageNextId (@Param("id") Long id);
}
