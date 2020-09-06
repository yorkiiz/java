package com.yorkiiz.boot.demo.dao;

import com.yorkiiz.boot.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Repository
public class ArticleJDBCDAO {


    //@Resource
    //JdbcTemplate primaryJdbcTemplate;
    @Resource
    JdbcTemplate secondaryJdbcTemplate;

    //@Resource(name = "primaryJdbcTemplate")
    //JdbcTemplate jdbcTemplate ;
    //JdbcTemplate jdbcTemplate = secondaryDataSource;

    @Resource(name = "secondaryJdbcTemplate")
    JdbcTemplate jdbcTemplate;

    public void save(Article article){
        jdbcTemplate.update("insert into article(title,content,author,createtime) values (?,?,?,?)",
                article.getTitle(),
                article.getContent(),
                article.getAuthor(),
                article.getCreatetime()
        );

    }

    public void delete(Long id){
        jdbcTemplate.update("delte from article where id = ?",id);
    }

    public Article findbyid(Long id){
        return (Article) jdbcTemplate.queryForObject("select * from article where id = ?",new Object[]{id},
                new BeanPropertyRowMapper<>(Article.class));

    }

    public List<Article> findAll(){
        return (List<Article>)jdbcTemplate.query("select * from article",
                new BeanPropertyRowMapper<>(Article.class));
    }

}
