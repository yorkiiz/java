package com.gupaoedu.example.springbootdemo02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Configuration
public class EndpointConfiguration {

    @Bean
    public CustomerMetricsIndicator customerMetricsIndicator(){
        return new CustomerMetricsIndicator();
    }
}
