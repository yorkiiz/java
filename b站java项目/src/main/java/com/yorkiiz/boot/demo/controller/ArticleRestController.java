package com.yorkiiz.boot.demo.controller;


import com.yorkiiz.boot.demo.model.AjaxResponse;
import com.yorkiiz.boot.demo.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
