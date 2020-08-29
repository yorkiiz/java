package com.yorkiiz.boot.demo.Serviece;

import com.yorkiiz.boot.demo.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ArticleService {

    public String saveaticle(Article article){
        log.info("saveArticle:{}",article);
        return "test";
    };
}
