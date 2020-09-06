package com.yorkiiz.boot.demo.Serviece;

import com.yorkiiz.boot.demo.model.Article;

import java.util.List;

public interface ArticleJDBCService {

    public void saveArticle(Article article);

    public void deleteArticle(Long id);


    public Article getArticle(Long id);

    public List<Article> getAll();
}
