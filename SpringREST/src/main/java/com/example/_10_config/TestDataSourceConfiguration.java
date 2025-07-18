package com.example._10_config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration(proxyBeanMethods = false)
@MapperScan(basePackages = "com.example._40_dao.testmapper",
			sqlSessionFactoryRef = "testSqlSessionFactory")
public class TestDataSourceConfiguration {
	
//	@Profile("dev")
//	@Qualifier("test")
//	@Bean(defaultCandidate = false)
//	@ConfigurationProperties("test.datasource")
//	public DataSourceProperties testDataSourceProperties() {
//		return new DataSourceProperties();
//	}
//	
//	@Profile("dev")
//	@Qualifier("test")
//	@Bean(defaultCandidate = false)
//	@ConfigurationProperties("test.datasource.configuration")
//	public HikariDataSource testDataSource(
//			@Qualifier("testDataSourceProperties") DataSourceProperties testDataSourceProperties) {
//		return testDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//	}
	
	@Profile("dev")
	@Qualifier("test")
	@Bean(defaultCandidate = false)
    @ConfigurationProperties("test.datasource")
    public DataSource testDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
        return dataSource;
    }
	
	@Profile("dev")
	@Qualifier("test")
	@Bean(defaultCandidate = false)
	public JdbcTemplate testJdbcTemplate(
			@Qualifier("test") DataSource testDataSource) {
		
		return new JdbcTemplate(testDataSource);
	}
	
	@Profile("dev")
	@Qualifier("test")
    @Bean(defaultCandidate = false)
    public PlatformTransactionManager testTransactionManager(
    		@Qualifier("test") DataSource dataSource) {
		
        return new DataSourceTransactionManager(dataSource);
    }
    
	/*
	 * SqlSessionFactoryBean for Mybatis
	 */
	@Profile("dev")
	@Qualifier("test")
	@Bean(defaultCandidate = false)
	public SqlSessionFactoryBean testSqlSessionFactory(
			@Qualifier("test") DataSource testDataSource) {
		
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(testDataSource);
		
		return sessionFactoryBean;
	}

}
