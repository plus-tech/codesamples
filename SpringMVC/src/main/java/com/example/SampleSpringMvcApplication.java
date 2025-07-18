package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
//@MapperScan("com.example._50_dao.mapper") // Package containing your Mapper interfaces
public class SampleSpringMvcApplication {
	private static final Logger log = LoggerFactory.getLogger(SampleSpringMvcApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SampleSpringMvcApplication.class, args);
	}

}
