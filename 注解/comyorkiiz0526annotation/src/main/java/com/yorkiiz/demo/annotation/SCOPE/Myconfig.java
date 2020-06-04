package com.yorkiiz.demo.annotation.SCOPE;

import com.yorkiiz.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Configuration
public class Myconfig {
    /*
     prototype 原型，多例
     singleton 单例
     request 主要应用于web，同一次请求，应用于web模块
     session 主要应用于web模块
     */
    @Scope("prototype")
    @Bean
    public Person person(){
        return new Person("yongjie",18);

    }


}
