package com.example._50_writer;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example._70_dto.User;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableBatchProcessing(
		dataSourceRef = "dataSource")
public class OrganizedBatchWriter {
	
//	@Autowired
//	DataSource dataSource;
	
//	@Qualifier("test")
	@Autowired
	DataSource dataSource;

	@Bean(name="obImportFileStepWriter")
	@StepScope
	public JdbcBatchItemWriter<User> obImportFileStepWriter() {
		
		String sql = "INSERT ALL "
				+ "INTO users(id, username, password) VALUES (users_seq.nextval, :username, :password) "
				+ "INTO authorities(username, authority) VALUES (:username, :role) "
				+ "SELECT * FROM DUAL";
		
		return new JdbcBatchItemWriterBuilder<User>()
				.sql(sql)
				.dataSource(dataSource)
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.build();
	}
}
