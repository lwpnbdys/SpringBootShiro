package com.lwp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lwp.mapper") //包扫描
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
