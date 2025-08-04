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
public class DbToDbBatchWriter {
		
	@Autowired
	@Qualifier("bcp")
	DataSource dataSource;

	@Bean(defaultCandidate=false)
	@Qualifier("bcp")
	@StepScope
	public JdbcBatchItemWriter<User> dtdImportFileStepWriter() {
		
		String sql = "INSERT ALL "
				+ "INTO users(id, username, password, enabled) VALUES (users_seq.nextval, :username, :password, :enabled) "
				+ "INTO authorities(username, authority) VALUES (:username, :role) "
				+ "SELECT * FROM DUAL";
		
		return new JdbcBatchItemWriterBuilder<User>()
				.sql(sql)
				.dataSource(dataSource)
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.build();
	}
}
