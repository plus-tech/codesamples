package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration(proxyBeanMethods = false)
public class TestDataSourceConfiguration {

	@Qualifier("test")
	@Bean(defaultCandidate = false)
	@ConfigurationProperties("test.datasource")
	public DataSourceProperties testDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Qualifier("test")
	@Bean(defaultCandidate = false)
	@ConfigurationProperties("test.datasource.configuration")
	public HikariDataSource testDataSource(
			@Qualifier("testDataSourceProperties") DataSourceProperties testDataSourceProperties) {
		return testDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	@Qualifier("test")
	@Bean(defaultCandidate = false)
	public JdbcTemplate jdbcTemplateOracle(
			@Qualifier("test") HikariDataSource testDataSource) {
		return new JdbcTemplate(testDataSource);
	}

}
