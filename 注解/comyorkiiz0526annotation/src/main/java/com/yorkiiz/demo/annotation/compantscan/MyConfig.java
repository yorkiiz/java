package com.yorkiiz.demo.annotation.compantscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Configuration
@ComponentScan(value="com.yorkiiz.project",
              // includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value=(Controller.class))}
        //includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value=(Mycontroller.class))}
        useDefaultFilters = false)
public class MyConfig {


}
