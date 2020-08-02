package com.yorkiiz.boot.demo;

import com.yorkiiz.boot.demo.model.LombookPOJO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootDemoApplication {

    LombookPOJO lombookPOJO = LombookPOJO.builder()
            .age(21)
            .name("yongjie")
            .build();
	public static void main(String[] args) {
		SpringApplication.run(BootDemoApplication.class, args);
	}

}
