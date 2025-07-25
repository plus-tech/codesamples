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
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example._70_dto.User;


@Configuration
@EnableBatchProcessing
public class DbToDbBatchConfig {

	public static final String JOB_NAME = "DbToDbJob";
	
	@Autowired
	public JobRepository jobRepository;
	
	@Autowired
	public PlatformTransactionManager transactionManager;
	
	/*
	 * Job flow
	 */
	@Bean(defaultCandidate = false)
	@Qualifier("bcp")
	public Job dtdImportUserJob() {
		return new JobBuilder(JOB_NAME, jobRepository)
				.incrementer(new RunIdIncrementer())
				.validator(dtdJobParamValidator())
				.start(truncateBcpStep())
				.next(importUserStep())
				.build();
	}

	
	@Qualifier("bcp")
	@Bean(defaultCandidate = false)
	public JobParametersValidator dtdJobParamValidator() {
		String[] requiredKeys = new String[]{"requiredKeys"};
		String[] optionalKeys = new String[]{"executedTime"};
		
		return new DefaultJobParametersValidator(requiredKeys, optionalKeys);
	}
	
	/*
	 * truncteStep: run a tasklet truncating users and authorities tables in bcp database
	 */
	@Autowired
	@Qualifier("bcp")
	MethodInvokingTaskletAdapter dtdTruncateStepTasklet;
	
	@Qualifier("bcp")
	@Bean(defaultCandidate = false)
	public Step truncateBcpStep() {
		return new StepBuilder("truncateStep", jobRepository)
				.tasklet(dtdTruncateStepTasklet, transactionManager)
				.build();
	}
	
	/*
	 * importUserStep: transfer data from the primary database to the bcp database
	 */
	@Autowired
	@Qualifier("bcp")
	JdbcCursorItemReader<User> dtdImportUserStepReader;
	
	@Autowired
	@Qualifier("bcp")
	ItemProcessor<User, User> dtdImportUserStepProcessor;
	
	@Autowired
	@Qualifier("bcp")
	JdbcBatchItemWriter<User> obImportUserStepWriter;
	
	@Qualifier("bcp")
	@Bean(defaultCandidate = false)
	public Step importUserStep() {
		return new StepBuilder("importFileStep", jobRepository)
				.<User, User>chunk(10, transactionManager)
				.reader(	dtdImportUserStepReader)
				.processor(dtdImportUserStepProcessor)
				.writer(obImportUserStepWriter)
				.build();
	}

}
