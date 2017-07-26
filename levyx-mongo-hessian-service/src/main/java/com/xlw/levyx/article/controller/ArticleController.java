package com.xlw.levyx.article.controller;

import com.xlw.levyx.article.service.ArticleService;
import com.xlw.levyx.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by levyx on 2017/7/25.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/foo")
    @ResponseBody
    public Object test(){
        System.out.println("test");
        return new HashMap<>();
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody Article article1){
        Article article = new Article();
        article.setId("1");
        article.setAuthorId("1");
        article.setAuthorName("张三");
        article.setContent("shdaklfhsadklfh稍等哈发了可视电话");
        articleService.saveArticle(article);
        articleService.saveArticle(article1);
        return new HashMap<>();
    }

    @RequestMapping(value = "get",method = RequestMethod.GET)
    @ResponseBody
    public Object save(@RequestParam("id")String articleId){
        return articleService.getArticle(articleId);
    }


}
