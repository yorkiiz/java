package com.feign2.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderServiceController {

    @Autowired
    OrderServiceFeign orderServiceFeign;

    @GetMapping("/test")
    public String test(){
        return orderServiceFeign.getOrders();
    }
}
