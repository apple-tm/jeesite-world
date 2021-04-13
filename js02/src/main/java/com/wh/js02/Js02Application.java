package com.wh.js02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@MapperScan("")

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class Js02Application {

	public static void main(String[] args) {
		SpringApplication.run(Js02Application.class, args);
	}

}
