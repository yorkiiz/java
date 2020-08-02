package com.yorkiiz.boot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther:
 * @date:
 * @describtion:
 **/
@Data
@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LombookPOJO {

    private String name;
    private Integer age;


}
