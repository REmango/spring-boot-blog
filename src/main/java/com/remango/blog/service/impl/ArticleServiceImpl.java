package com.remango.blog.service.impl;

import com.remango.blog.dao.ArticleDao;
import com.remango.blog.dao.TimelineDao;
import com.remango.blog.entity.Article;
import com.remango.blog.entity.PageList;
import com.remango.blog.entity.Timeline;
import com.remango.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by li on 2019/3/14.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private TimelineDao timelineDao;

    @Override
    public PageList<Article> queryArticleList(String key, Integer offset, Integer limit){
        PageList<Article> articlePageList = new PageList<>();
        List<Article> articleList = articleDao.queryArticleListByKey(key, offset, limit);
        int total = articleDao.queryArticleTotalByKey(key);

        articlePageList.setRows(articleList);
        articlePageList.setTotal(total);
        return articlePageList;

    }
    @Override
    public PageList<Article> queryArticleListByTimeLine(String TimeLine, Integer offset, Integer limit){
        PageList<Article> articlePageList = new PageList<>();
        List<Article> articleList = articleDao.queryArticleListByTimeLine(TimeLine, offset, limit);
        int total = articleDao.queryArticleTotalByTimeLine(TimeLine);

        articlePageList.setRows(articleList);
        articlePageList.setTotal(total);
        return articlePageList;

    }

    @Override
    public PageList<Article> queryArticleListByVisits(Integer offset, Integer limit) {
        PageList<Article> articlePageList = new PageList<>();
        List<Article> articleList = articleDao.queryArticleListByVisits(offset, limit);
        int total = articleDao.queryArticleTotalByKey("");
        articlePageList.setRows(articleList);
        articlePageList.setTotal(total);
        return articlePageList;
    }

    @Override
    @Transactional
    public void saveArticle(Article article){
        Date now = new Date();
        long articleId = System.currentTimeMillis();
        // 设置时间轴
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月");

        String timelineName = dateFormat.format(new Date());

        Article preArticle = articleDao.queryPrePageId();

        if(preArticle != null) {
            long nextArticleId = preArticle.getId() + 1;
            // 更新上一页的下页id
            articleDao.updatePrePageNextId(nextArticleId);
            article.setLastArticleId(preArticle.getId());
        }

        article.setTimeline(timelineName);
        article.setCreateTime(now);
        article.setArticleId(articleId);
        article.setVisits(1);


        articleDao.saveArticle(article);

        if (timelineDao.queryTimelineListByName(timelineName).size() == 0) {
            Timeline timeline = new Timeline();
            timeline.setTimeline(timelineName);
            timeline.setCreateTime(now);
            timelineDao.saveTimeline(timeline);
        }
    }
    @Override
    public Article queryArticle(Long articleId, String author){
        return articleDao.queryArticle(articleId, author);
    }
    @Override
    public void updateArticleVisits(Map<String, Object> map) {
        articleDao.updateArticleVisits(map);
    }

    @Override
    public Article queryArticleInfoById(Long id) {
        return articleDao.queryArticleInfoById(id);
    }
}
