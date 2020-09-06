package com.yorkiiz.boot.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {

    @JsonIgnore
    private Long id;
    private String title;
    private String content;
    private String author;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    private List<Reader> reader;

}
