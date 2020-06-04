package com.yorkiiz.demo.annotation.Lifecycle;



import com.yorkiiz.project.entity.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Configuration
@ComponentScan("com.yorkiiz.project.entity")
public class Myconfig {


    @Lazy
    @Bean(initMethod = "addoil", destroyMethod = "stop")
    public Car car(){
        return new Car();
    }


}
