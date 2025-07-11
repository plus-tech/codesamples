package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringBatchApplication {
	
	private static final Logger Log = LoggerFactory.getLogger(SpringBatchApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(
			JobLauncher jobLauncher, 
//			@Qualifier("importUserJob") Job importUserJob) {
			@Qualifier("obImportUserJob") Job importUserJob,
			@Value("${import.path}") String importPath,
			@Value("${file.users}") String fileUsers) {
		
	    return args -> {
	        JobParameters jobParameters = new JobParametersBuilder()
	        			.addString("filePath", importPath + fileUsers)
	                .addLong("executedTime", System.currentTimeMillis())
	                .toJobParameters();
	        jobLauncher.run(importUserJob, jobParameters);
	    };
	}
}
