package com.yorkiiz.boot.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yorkiiz.boot.demo.Serviece.ArticleService;
import com.yorkiiz.boot.demo.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest

public class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    /*
    @Before
    private static void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController()).build();
    }
    */


    @Test
    public void saveArticle() throws  Exception{

        String article = "{\n" +
                "    \"id\": 1,\n" +
                "    \"author\": \"zimug\",\n" +
                "    \"title\": \"手摸手教你开发spring boot\",\n" +
                "    \"content\": \"c\",\n" +
                "    \"createTime\": \"2017-07-16 05:23:34\",\n" +
                "    \"reader\":[{\"name\":\"yongjie\",\"age\":18},{\"name\":\"zhouxu\",\"age\":16}]\n" +
                "}";

      //  ObjectMapper objectMapper = new ObjectMapper();
      //  Article articleobj = objectMapper.readValue(article,Article.class);
      // when(articleService.saveaticle(articleobj)).thenReturn("ok");

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                .request(HttpMethod.POST,"/rest/articles")
                .contentType("application/json")
                .content(article)
        )
                .andDo(print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }


}
