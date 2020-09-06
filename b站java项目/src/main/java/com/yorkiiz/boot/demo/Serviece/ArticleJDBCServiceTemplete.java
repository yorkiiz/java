package com.yorkiiz.boot.demo.Serviece;

import com.yorkiiz.boot.demo.dao.ArticleJDBCDAO;
import com.yorkiiz.boot.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Service
public class ArticleJDBCServiceTemplete implements ArticleJDBCService {


    @Resource
    ArticleJDBCDAO articleJDBCDAO;



    @Override
    public void saveArticle(Article article) {
       articleJDBCDAO.save(article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleJDBCDAO.delete(id);

    }

    @Override
    public Article getArticle(Long id) {
        return articleJDBCDAO.findbyid(id);
    }

    @Override
    public List<Article> getAll() {
        return articleJDBCDAO.findAll();
    }
}
