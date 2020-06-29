package com.gupaoedu.example.springbootdemo02;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Endpoint(id="customer")
public class CustomerMetricsIndicator {

    @ReadOperation
    public Map<String,Object> time(){
        Map<String,Object> result=new HashMap<>();
        Date time=new Date();
        result.put("当前时间:",time.toString());
        return result;
    }

}
