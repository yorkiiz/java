package com.yorkiiz.demo.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class MyTest {

    @Test
    public void test() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object object = app.getBean("person");
        System.out.println(object);
    }


}
