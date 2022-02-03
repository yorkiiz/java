package com.feign.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderService {

    @RequestMapping("/orders")
    public String getAllOrders(){
        return "AllOrders success";
    }
}
