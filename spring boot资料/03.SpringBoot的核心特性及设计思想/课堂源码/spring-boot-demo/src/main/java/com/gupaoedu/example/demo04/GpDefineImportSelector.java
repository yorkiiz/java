package com.gupaoedu.example.demo04;

import com.gupaoedu.example.demo02.RedisConfiguration;
import com.gupaoedu.example.demo03.GpSqlSessionFactory;
import com.gupaoedu.example.demo03.MybatisConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class GpDefineImportSelector implements ImportSelector{
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //动态导入bean, 告诉了Spring  ， 两个配置类在哪里

        //TODO 在这里去加载所有的配置类就行？
        // 通过某种机制去完成指定路径的配置类的扫描就行？
        //package.class.classname
        return new String[]{GpSqlSessionFactory.class.getName(), RedisConfiguration.class.getName()};
    }
}
