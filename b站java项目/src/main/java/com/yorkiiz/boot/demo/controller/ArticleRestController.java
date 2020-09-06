package com.yorkiiz.boot.demo.controller;


import com.yorkiiz.boot.demo.Serviece.ArticleJDBCService;
import com.yorkiiz.boot.demo.Serviece.ArticleService;
import com.yorkiiz.boot.demo.model.AjaxResponse;
import com.yorkiiz.boot.demo.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/
@RestController
@Slf4j
@RequestMapping("/rest")
public class ArticleRestController {

    //@Resource
    //ArticleService articleService;

    @Resource(name = "articleJDBCServiceTemplete")
    ArticleJDBCService articleJDBCService;

    //@RequestMapping(value = "/Articles/{id}",method = RequestMethod.GET)

    @GetMapping( "/Articles/{id}")
    public AjaxResponse getArticle(@PathVariable("id")Long id){

       // Article article = Article.builder().id(1L).author("zimug").content("spring boot 2.深入浅出").title("t1").build();

        Article article = articleJDBCService.getArticle(id);
        log.info("Article"+article);

        return AjaxResponse.success(article);
    }

    @GetMapping( "/Articles")
    public @ResponseBody AjaxResponse getallArticle(){

        //Article articles = Article.builder().id(1L).author("zimug").content("spring boot 2.深入浅出").title("t1").build();
        List<Article> articles = articleJDBCService.getAll();
        log.info("Articles"+articles);

        return AjaxResponse.success(articles);
    }

    //@RequestMapping(value = "/Articles/{id}",method = RequestMethod.POST)
    @PostMapping( "/articles")
    public AjaxResponse saveArticle(@RequestBody Article article){

        articleJDBCService.saveArticle(article);
        log.info("Article"+article);

        return AjaxResponse.success(article);
    }

    //@RequestMapping(value = "/Articles/{id}",method = RequestMethod.PUT)
    @PutMapping(value = "/Articles/{id}")
    public AjaxResponse updateArticle(@RequestBody Article article){

        if(article.getId() == null){
            //TODO抛出一个异常
        }


        log.info("Article"+article);

        return AjaxResponse.success(article);
    }


    //@RequestMapping(value = "/Articles/{id}",method = RequestMethod.DELETE)
    @DeleteMapping("/Articles/{id}")
    public AjaxResponse deleteArticle(@PathVariable("id")Long id){

        articleJDBCService.deleteArticle(id);
        log.info("Article"+ id);

        return AjaxResponse.success();
    }

}
