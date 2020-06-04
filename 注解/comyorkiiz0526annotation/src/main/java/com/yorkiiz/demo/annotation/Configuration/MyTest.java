package com.yorkiiz.demo.annotation.Configuration;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class MyTest {

    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(Myconfig.class);
        Object bean = app.getBean("person");
        Object bean2 = app.getBean("person");
        System.out.println(bean);
        System.out.println(bean==bean2);
    }


}
