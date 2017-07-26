package com.xlw.levyx.article.service;

import com.xlw.levyx.model.Article;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by levyx on 2017/7/26.
 */
@Service
public class ArticleService {
    @Autowired
    private MongoTemplate mongoTemplate;
    public static final String COLLECTION_ARTICLES = "articles";

    public void saveArticle(Article article){
        mongoTemplate.save(article,COLLECTION_ARTICLES);
    }

    public Article getArticle(String id){
        return mongoTemplate.findById(id,Article.class,COLLECTION_ARTICLES);
    }

    //根据查询条件去mongo 中查询满足条件的病历
    public List<Article> getList(Article article,String commentContent,Integer limit,Integer offset) {

        String authorName = article.getAuthorName();
        String authorId = article.getAuthorId();
        String content = article.getContent();
        String id = article.getId();

        //五个条件，取【与】操作

        //精确查询
        Criteria cAuthorName;
        authorName = authorName == null ? null : authorName.trim();          //去掉字符串首尾空格
        cAuthorName = (authorName == null || "".equals(authorName)) ? new Criteria() : where("authorName").regex("^"+authorName+"$","i");

        Criteria cAuthorId;
        authorId = authorId == null ? null : authorId.trim();
        cAuthorId = (authorId == null || "".equals(authorId)) ? new Criteria() : where("authorId").is(authorId);

        Criteria cContent;
        content = content == null ? null : content.trim();
        cContent = (content == null || "".equals(content)) ? new Criteria() : where("content").regex("^" + content + "$", "i");

        Criteria cId;
        id = id == null ? null : id.trim();
        cId = (id == null || "".equals(id)) ? new Criteria() : where("_id").is(id);

        //评论内容模糊查询
        Criteria cCommentContent;
        commentContent = commentContent == null ? null : commentContent.trim();
        cCommentContent = (commentContent == null || "".equals(commentContent)) ? new Criteria() : where("commentList.content").regex(commentContent, "i");


        Criteria c = new Criteria().andOperator(cAuthorName, cAuthorId, cContent, cId,cCommentContent);
        Query query = query(c);
        //排序
        query.with(new Sort(Sort.Direction.DESC, "_id"));

        //计算满足条件的总记录数
        long total = mongoTemplate.count(query, Article.class, COLLECTION_ARTICLES);
        /*//指定返回的域为 _id
        query.fields().include("_id");*/
        //分页
        query.skip((offset-1) * limit).limit(limit);

        List<Article> list = mongoTemplate.find(query, Article.class,COLLECTION_ARTICLES);

        return list;
    }
}
