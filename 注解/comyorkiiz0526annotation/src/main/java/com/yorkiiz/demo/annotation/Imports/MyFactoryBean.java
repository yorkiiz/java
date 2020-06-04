package com.yorkiiz.demo.annotation.Imports;

import com.yorkiiz.project.entity.Dog;
import org.springframework.beans.factory.FactoryBean;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class MyFactoryBean implements FactoryBean {


    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
