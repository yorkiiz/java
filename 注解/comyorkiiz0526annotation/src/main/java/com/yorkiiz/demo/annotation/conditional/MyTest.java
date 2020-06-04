package com.yorkiiz.demo.annotation.conditional;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class MyTest {

    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(Myconfig.class);
        Object bean2 = app.getBean("Tom");
        System.out.println(bean2);


    }


}
