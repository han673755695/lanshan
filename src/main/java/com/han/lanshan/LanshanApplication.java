package com.han.lanshan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@MapperScan("com.han.lanshan.**.dao")
@SpringBootApplication
@EnableSwagger2
public class LanshanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanshanApplication.class, args);
		System.out.println("启动完毕...");
	}

}
