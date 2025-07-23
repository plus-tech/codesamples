package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
//@MapperScan("com.example._50_dao.mapper")
public class SampleSpringMvcApplication {
	private static final Logger log = LoggerFactory.getLogger(SampleSpringMvcApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SampleSpringMvcApplication.class, args);
	}

}
