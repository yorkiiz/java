package com.yorkiiz.demo.annotation.Imports;

import com.yorkiiz.project.entity.Cat;
import com.yorkiiz.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Configuration
@Import(value={Cat.class, Myimportselector.class})
public class Myconfig {



    @Bean
    public Person person(){
        System.out.println("将对象加载到IOC容器中");
        return new Person("yongjie",18);

    }




}
