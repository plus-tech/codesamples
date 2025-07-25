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
import org.springframework.core.annotation.Order;


@SpringBootApplication
public class SpringBatchApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SpringBatchApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

	@Bean
	@Order(1)
	public CommandLineRunner runImportFileJob(
			JobLauncher jobLauncher, 
			Job importUserJob,
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
	
	@Bean
	@Order(2)
	public CommandLineRunner runImportUserJob(
			JobLauncher jobLauncher, 
			@Qualifier("bcp") Job importUserJob) {
		
	    return args -> {
	        JobParameters jobParameters = new JobParametersBuilder()
	        		.addString("requiredKeys", "No keys required")
	        		.addLong("executedTime", System.currentTimeMillis())
	        		.toJobParameters();
	        jobLauncher.run(importUserJob, jobParameters);
	    };
	}
}
