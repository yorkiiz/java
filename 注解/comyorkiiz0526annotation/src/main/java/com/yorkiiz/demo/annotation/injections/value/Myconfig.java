package com.yorkiiz.demo.annotation.injections.value;

import com.yorkiiz.project.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Configuration
@PropertySource("classpath:values.properties")
public class Myconfig {



    @Bean
    public Bird bird(){
        System.out.println("将对象加载到IOC容器中");
        return new Bird();

    }




}
