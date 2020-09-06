package com.yorkiiz.boot.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


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


    @Id
    private Long id;
    private String author;
    private String title;
    private String content;



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;


}
