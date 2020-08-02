package com.yorkiiz.boot.demo.Controleler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String Hello(String name){
        return "hello--world"+name;
    }
}
