package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@MapperScan(basePackages = "org.sang.mybatis.mapper")
public class GreenDeanBackApplication {

	public static void main(String[] args)  {
		SpringApplication.run(GreenDeanBackApplication.class, args);
	}

}
