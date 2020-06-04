package com.yorkiiz.demo.annotation.Lifecycle;



import com.yorkiiz.project.entity.Car;
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
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Myconfig.class);
        //Car car = (Car) app.getBean("car");
        //System.out.println("============"+car);
        //car.run();


        app.close();

    }


}
