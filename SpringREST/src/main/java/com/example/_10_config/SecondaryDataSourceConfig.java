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
@MapperScan(basePackages = "com.example._40_dao.sndmapper",
			sqlSessionFactoryRef = "sndSqlSessionFactory")
public class SecondaryDataSourceConfig {
	
//	@Profile("dev")
//	@Qualifier("secondary")
//	@Bean(defaultCandidate = false)
//	@ConfigurationProperties("secondary.datasource")
//	public DataSourceProperties sndDataSourceProperties() {
//		return new DataSourceProperties();
//	}
//	
//	@Profile("dev")
//	@Qualifier("secondary")
//	@Bean(defaultCandidate = false)
//	@ConfigurationProperties("secondary.datasource.configuration")
//	public HikariDataSource sndDataSource(
//			@Qualifier("secondary") DataSourceProperties sndDataSourceProperties) {
//		return sndDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//	}
	
	@Profile("dev")
	@Qualifier("secondary")
	@Bean(defaultCandidate = false)
    @ConfigurationProperties("secondary.datasource")
    public DataSource sndDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
        return dataSource;
    }
	
	@Profile("dev")
	@Qualifier("secondary")
	@Bean(defaultCandidate = false)
	public JdbcTemplate sndJdbcTemplate(
			@Qualifier("secondary") DataSource dataSource) {
		
		return new JdbcTemplate(dataSource);
	}
	
	@Profile("dev")
	@Qualifier("secondary")
    @Bean(defaultCandidate = false)
    public PlatformTransactionManager sndTransactionManager(
    		@Qualifier("secondary") DataSource dataSource) {
		
        return new DataSourceTransactionManager(dataSource);
    }
    
	/*
	 * SqlSessionFactoryBean for Mybatis
	 */
	@Profile("dev")
	@Qualifier("secondary")
	@Bean(defaultCandidate = false)
	public SqlSessionFactoryBean sndSqlSessionFactory(
			@Qualifier("secondary") DataSource dataSource) {
		
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		
		return sessionFactoryBean;
	}

}
