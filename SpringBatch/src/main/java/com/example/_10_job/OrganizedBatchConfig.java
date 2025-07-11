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
public class OrganizedBatchConfig {

	public static final String JOB_NAME = "layeredFileToDbJob";
	
	@Autowired
	public JobRepository jobRepository;
	
	@Autowired
	public PlatformTransactionManager transactionManager;
	
	
	@Bean(name="obImportUserJob")
	public Job obImportUserJob() {
		return new JobBuilder(JOB_NAME, jobRepository)
				.incrementer(new RunIdIncrementer())
				.validator(jobParametersValidator())
				.start(truncateStep())
				.next(importFileStep())
				.build();
	}

	@Bean
	public JobParametersValidator jobParametersValidator() {
		String[] requiredKeys = new String[]{"filePath"};
		String[] optionalKeys = new String[]{"executedTime"};
		
		return new DefaultJobParametersValidator(requiredKeys, optionalKeys);
	}
	
	@Autowired
	@Qualifier("obTruncateStepTasklet")
	MethodInvokingTaskletAdapter obTruncateStepTasklet;
	
	@Bean
	public Step truncateStep() {
		return new StepBuilder("truncateStep", jobRepository)
				.tasklet(obTruncateStepTasklet, transactionManager)
				.build();
	}
	
	@Autowired
	@Qualifier("obImportFileStepReader")
	FlatFileItemReader<User> obImportFileStepReader;
	
	@Autowired
	@Qualifier("obImportFileStepProcessor")
	ItemProcessor<User, User> obImportFileStepProcessor;
	
	@Autowired
	@Qualifier("obImportFileStepWriter")
	JdbcBatchItemWriter<User> obImportFileStepWriter;
	
	@Bean
	public Step importFileStep() {
		return new StepBuilder("importFileStep", jobRepository)
				.<User, User>chunk(10, transactionManager)
				.reader(obImportFileStepReader)
				.processor(obImportFileStepProcessor)
				.writer(obImportFileStepWriter)
				.build();
	}

}
