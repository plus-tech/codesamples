package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.example._40_dao.mapper")
public class SpringRestApplication {
	
	@Autowired
	private Logger log;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

}
