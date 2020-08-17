package com.yorkiiz.boot.demo.controller;


import com.yorkiiz.boot.demo.model.AjaxResponse;
import com.yorkiiz.boot.demo.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @auther:
 * @date:
 * @describtion:
 **/
@RestController
@Slf4j
public class ArticleRestController {

    @RequestMapping(value = "/Articles/{id}",method = RequestMethod.GET)
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

    @RequestMapping(value = "/Articles/{id}",method = RequestMethod.POST)
    public AjaxResponse saveArticle(@RequestBody Article article){

        log.info("Article"+article);

        return AjaxResponse.success();
    }

    @RequestMapping(value = "/Articles/{id}",method = RequestMethod.PUT)
    public AjaxResponse updateArticle(@RequestBody Article article){

        if(article.getId() == null){
            //TODO抛出一个异常
        }

        log.info("Article"+article);

        return AjaxResponse.success(article);
    }


    @RequestMapping(value = "/Articles/{id}",method = RequestMethod.DELETE)
    public AjaxResponse deleteArticle(@PathVariable("id")Long id){

        log.info("Article"+ id);

        return AjaxResponse.success();
    }

}
