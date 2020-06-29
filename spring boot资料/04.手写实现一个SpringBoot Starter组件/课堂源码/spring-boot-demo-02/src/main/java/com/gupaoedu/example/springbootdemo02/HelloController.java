package com.gupaoedu.example.springbootdemo02;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class HelloController {

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/say")
    public String say(){
        RBucket bucket=redissonClient.getBucket("name");
        if(bucket.get()==null){
            bucket.set("gupaoedu.com");
        }
        return bucket.get().toString();
    }
}
