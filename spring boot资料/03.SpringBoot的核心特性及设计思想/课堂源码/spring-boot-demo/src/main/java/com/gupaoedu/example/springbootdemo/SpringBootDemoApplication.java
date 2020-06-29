package com.gupaoedu.example.springbootdemo;

import com.gupaoedu.autoconfiguration.demo.GupaoEduCore;
import com.gupaoedu.example.demo02.GpRedisTemplate;
import com.gupaoedu.example.demo03.GpSqlSessionFactory;
import com.gupaoedu.example.demo04.EnableConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@EnableConfiguration
@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ca=SpringApplication.run(SpringBootDemoApplication.class, args);
		System.out.println(ca.getBean(GupaoEduCore.class));
	}
}
