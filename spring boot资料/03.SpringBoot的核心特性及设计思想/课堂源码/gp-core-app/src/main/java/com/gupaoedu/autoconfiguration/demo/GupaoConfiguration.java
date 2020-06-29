package com.gupaoedu.autoconfiguration.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
/*@ConditionalOnClass({RedisOperations.class})*/

@Configuration
public class GupaoConfiguration {

    @Bean
    public GupaoEduCore gupaoEduCore(){
        return new GupaoEduCore();
    }
}
