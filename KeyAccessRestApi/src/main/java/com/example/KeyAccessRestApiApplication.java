package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example._40_dao.mapper")
public class KeyAccessRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeyAccessRestApiApplication.class, args);
	}

}
