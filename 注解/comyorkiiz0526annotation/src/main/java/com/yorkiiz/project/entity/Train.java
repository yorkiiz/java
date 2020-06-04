package com.yorkiiz.project.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Component
public class Train implements InitializingBean, DisposableBean {


    @Override
    public void destroy() throws Exception {
        System.out.println("火车对象销毁");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("火车对象初始化");

    }
}
