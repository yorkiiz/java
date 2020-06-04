package com.yorkiiz.demo.annotation.LAZY;

import com.yorkiiz.project.entity.Car;
import com.yorkiiz.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Configuration
public class Myconfig {


    @Lazy
    @Bean
    public Person person(){
        System.out.println("将对象加载到IOC容器中");
        return new Person("yongjie",18);

    }




}
