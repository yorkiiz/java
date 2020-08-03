package com.yorkiiz.boot.demo.model;

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
public class Article {

    private Long id;
    private String title;
    private String author;
    private String content;
    private Date createtime;
    private List<Reader> reader;

}
