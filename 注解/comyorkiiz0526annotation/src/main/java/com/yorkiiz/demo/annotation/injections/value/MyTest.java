package com.yorkiiz.demo.annotation.injections.value;

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
        System.out.println("IOC容器创建完成");
        System.out.println(app.getBean("bird"));

    }


}
