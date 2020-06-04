package com.yorkiiz.demo.annotation.Imports;

import com.yorkiiz.project.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import com.yorkiiz.project.entity.Dog;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class MyTest {

    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(Myconfig.class);
        System.out.println("将容器初始化");


        String [] beanNames = app.getBeanDefinitionNames();
        System.out.println(app);
        System.out.println(Arrays.toString(beanNames)
                           .replaceAll("\\[|\\]","")
                           .replaceAll(", ","\n"));

    }


}
