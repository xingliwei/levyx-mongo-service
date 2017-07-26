package com.xlw.levyx.article.hessian;

import com.xlw.levyx.model.Article;

import java.util.List;

/**
 * Created by levyx on 2017/7/25.
 */
public interface ArticleHessian {
    String hello(String msg);

    void save(Article article);

    Article getArticle(String id);

    List<Article> getArticleList(Article article,String commentContent,Integer limit,Integer offset);
}
