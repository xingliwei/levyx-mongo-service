package com.xlw.levyx.article.hessian;

import com.caucho.hessian.server.HessianServlet;
import com.xlw.levyx.article.service.ArticleService;
import com.xlw.levyx.model.Article;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by levyx on 2017/7/25.
 */
public class ArticleHessianImpl extends HessianServlet implements ArticleHessian {
    @Autowired
    private ArticleService articleService;

    public String hello(String msg) {
        System.out.println("from hessian service,client msg:" + msg);
        return "hessian service!";
    }

    @Override
    public void save(Article article) {
        articleService.saveArticle(article);
    }

    @Override
    public Article getArticle(String id) {
        return articleService.getArticle(id);
    }

    @Override
    public List<Article> getArticleList(Article article,String commentContent, Integer limit, Integer offset) {
        return articleService.getList(article,commentContent,limit,offset);
    }
}
