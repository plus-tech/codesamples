package com.example._10_job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.transaction.PlatformTransactionManager;

import com.example._70_dto.User;


@Configuration
@EnableBatchProcessing
public class SimpleBatchConfig {

	@Autowired
	public JobRepository jobRepository;
	
	@Autowired
	public PlatformTransactionManager transactionManager;

	/*
	 * A Step typically consists of an ItemReader, ItemProcessor (optional), and ItemWriter.
	 */
	@Bean(name="importUserJob")
	public Job importUserJob(Step step1) {
	     return new JobBuilder("importUserJob", jobRepository)
	             .incrementer(new RunIdIncrementer())
	             .flow(step1)
	             .end()
	             .build();
	}
	
	@Bean
	public Step step1(JdbcBatchItemWriter<User> writer) {
		return new StepBuilder("step1", jobRepository)
				.<User, User>chunk(10, transactionManager)
				.reader(reader())
				.processor(processor())
				.writer(writer)
				.build();
	}

	@Bean
	public FlatFileItemReader<User> reader() {
		return new FlatFileItemReaderBuilder<User>()
				.name("UserItemReader")
				.resource(new ClassPathResource("users.csv"))
				.delimited()
				.names(new String[]{"username", "password", "role"})
				.targetType(User.class)
				.build();
	}
	
	@Bean
	public ItemProcessor<User, User> processor() {
		return item -> {
		    	 /*
		    	  * Transforming data: encrypt the password
		    	  */
			item.setUsername(item.getUsername());
			item.setPassword(
					PasswordEncoderFactories
					.createDelegatingPasswordEncoder()
					.encode(item.getPassword()));
			item.setRole(item.getRole());
			
			return item;
		};
	}
		
	@Bean
	public JdbcBatchItemWriter<User> writer(@Autowired DataSource dataSource) {
		
		String sql = "INSERT INTO users(id, username, password, role) VALUES (users_seq.nextval, :username, :password, :role)";
		
		return new JdbcBatchItemWriterBuilder<User>()
				.sql(sql)
				.dataSource(dataSource)
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.build();
	}
	
}
