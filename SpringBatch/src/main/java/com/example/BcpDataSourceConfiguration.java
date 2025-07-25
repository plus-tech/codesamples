package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration(proxyBeanMethods = false)
public class BcpDataSourceConfiguration {

	@Qualifier("bcp")
	@Bean(defaultCandidate = false)
	@ConfigurationProperties("bcp.datasource")
	public DataSourceProperties bcpDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Qualifier("bcp")
	@Bean(defaultCandidate = false)
	@ConfigurationProperties("bcp.datasource.configuration")
	public HikariDataSource bcpDataSource(
			@Qualifier("bcpDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	@Qualifier("bcp")
	@Bean(defaultCandidate = false)
	public JdbcTemplate bcpJdbcTemplate(
			@Qualifier("bcp") HikariDataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
