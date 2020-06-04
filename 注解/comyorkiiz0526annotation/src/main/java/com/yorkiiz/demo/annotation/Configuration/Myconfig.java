package com.yorkiiz.demo.annotation.Configuration;

import com.yorkiiz.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Configuration
public class Myconfig {
    @Bean
    public Person person(){
        return new Person("yongjie",18);

    }


}
