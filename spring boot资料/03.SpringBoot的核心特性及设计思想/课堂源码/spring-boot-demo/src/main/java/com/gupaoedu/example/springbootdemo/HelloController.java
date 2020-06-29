package com.gupaoedu.example.springbootdemo;

import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class HelloController {


   /* @Autowired //在这里能够实现注入的前提是？ IOC存在实例（自动装配）
    private RedisTemplate<String,String> redisTemplate;*/


    /*@GetMapping("/say")
    public String say(){
        return redisTemplate.opsForValue().get("name");
    }*/

}
