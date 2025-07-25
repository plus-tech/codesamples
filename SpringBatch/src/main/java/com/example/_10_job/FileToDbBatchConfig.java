package com.example._10_job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example._70_dto.User;


@Configuration
@EnableBatchProcessing
public class FileToDbBatchConfig {

	public static final String JOB_NAME = "FileToDbJob";
	
	@Autowired
	public JobRepository jobRepository;
	
	@Autowired
	public PlatformTransactionManager transactionManager;
	
	/*
	 * Job flow
	 */
	@Bean
	public Job ftdImportUserJob() {
		return new JobBuilder(JOB_NAME, jobRepository)
				.incrementer(new RunIdIncrementer())
				.validator(ftdJobParamValidator())
				.start(truncateStep())
				.next(importFileStep())
				.build();
	}
	

	@Bean
	public JobParametersValidator ftdJobParamValidator() {
		String[] requiredKeys = new String[]{"filePath"};
		String[] optionalKeys = new String[]{"executedTime"};
		
		return new DefaultJobParametersValidator(requiredKeys, optionalKeys);
	}
	
	/*
	 * truncteStep: run a tasklet truncating users and authorities tables
	 */
	@Autowired
	MethodInvokingTaskletAdapter ftdTruncateStepTasklet;
	
	@Bean
	public Step truncateStep() {
		return new StepBuilder("truncateStep", jobRepository)
				.tasklet(ftdTruncateStepTasklet, transactionManager)
				.build();
	}
	
	/*
	 * importFileStep: read the csv file and write to the database
	 */
	@Autowired
	FlatFileItemReader<User> ftdImportFileStepReader;
	
	@Autowired
	ItemProcessor<User, User> ftdImportFileStepProcessor;
	
	@Autowired
	JdbcBatchItemWriter<User> ftdImportFileStepWriter;
	
	@Bean
	public Step importFileStep() {
		return new StepBuilder("importFileStep", jobRepository)
				.<User, User>chunk(10, transactionManager)
				.reader(ftdImportFileStepReader)
				.processor(ftdImportFileStepProcessor)
				.writer(ftdImportFileStepWriter)
				.build();
	}

}
