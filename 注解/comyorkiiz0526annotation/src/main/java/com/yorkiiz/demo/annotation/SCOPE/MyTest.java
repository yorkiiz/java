package com.yorkiiz.demo.annotation.SCOPE;

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
        Object bean = app.getBean("person");
        Object bean2 = app.getBean("person");
        System.out.println(bean.hashCode());
        System.out.println(bean2.hashCode());
        System.out.println(bean==bean2);
    }


}
