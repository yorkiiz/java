package com.yorkiiz.demo.annotation.conditional;

import com.yorkiiz.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Configuration
public class Myconfig {
    @Conditional(WindowsOs.class)
    @Bean
    public Person Tom(){
        System.out.println("将Tom初始化到IOC容器");
        return new Person("Tom",18);

    }


    @Bean
    @Conditional(WindowsOs.class)
    public Person Mic(){
        System.out.println("将Mic初始化到IOC容器");
        return new Person("mic",18);

    }



    @Bean
    @Conditional(LinuxOs.class)
    public Person Jie(){
        System.out.println("将Jie初始化到IOC容器");
        return new Person("Jie",18);

    }


}
