package com.example._90_util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.SpringRestApplication;

@Configuration
public class LoggerConfig {
	
	@Bean
	public Logger log() {
		return(LoggerFactory.getLogger(SpringRestApplication.class));
	}

}
