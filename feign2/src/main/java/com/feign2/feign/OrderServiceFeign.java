package com.feign2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="com.feign.FeignApplication",url="localhost:9210")
public interface OrderServiceFeign {
    @RequestMapping("/orders")
    String getOrders();
}
