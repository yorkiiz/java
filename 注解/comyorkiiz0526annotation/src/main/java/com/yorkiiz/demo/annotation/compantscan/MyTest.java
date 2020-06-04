package com.yorkiiz.demo.annotation.compantscan;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @auther:
 * @date:
 * @describtion:
 **/


public class MyTest {

    @Test
    public void test() {


        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        String[] beanNames = app.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanNames));
    }

}
