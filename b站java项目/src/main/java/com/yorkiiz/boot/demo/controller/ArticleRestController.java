package com.yorkiiz.boot.demo.controller;


import com.yorkiiz.boot.demo.Serviece.ArticleService;
import com.yorkiiz.boot.demo.model.AjaxResponse;
import com.yorkiiz.boot.demo.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @auther:
 * @date:
 * @describtion:
 **/
@RestController
@Slf4j
@RequestMapping("/rest")
public class ArticleRestController {

    @Resource
    ArticleService articleService;

    //@RequestMapping(value = "/Articles/{id}",method = RequestMethod.GET)
    @GetMapping( "/Articles/{id}")
    public AjaxResponse getArticle(@PathVariable("id")Long id){
        Article article = Article.builder()
                .author("yongjie")
                .content("spring练手")
                .createtime(new Date())
                .id(1L)
                .title("spring learn")
                .build();
        log.info("Article"+article);

        return AjaxResponse.success(article);
    }

    //@RequestMapping(value = "/Articles/{id}",method = RequestMethod.POST)
    @PostMapping( "/articles")
    public AjaxResponse saveArticle(@RequestBody Article article){

        log.info("Article"+article);

        return AjaxResponse.success(articleService.saveaticle(article));
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

        log.info("Article"+ id);

        return AjaxResponse.success();
    }

}
