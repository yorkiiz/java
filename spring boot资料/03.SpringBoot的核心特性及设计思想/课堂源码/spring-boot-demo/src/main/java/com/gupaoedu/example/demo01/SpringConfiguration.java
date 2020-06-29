package com.gupaoedu.example.demo01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Configuration
public class SpringConfiguration {

    /**
     * 在某个环境下不装在
     * 或者不满足某个条件的时候，不装在
     * 或者，如果已经装载过了，就不重复装载
     * 。。。
     * @return
     */
    @Conditional(GpCondition.class)
    @Bean
    public DemoService demoService(){
        return new DemoService();
    }
}
