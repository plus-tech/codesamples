package com.example._10_config;

import javax.sql.DataSource;

//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


/*
 * Create a customized DataSource
 */

@Configuration(proxyBeanMethods = false)
//@PropertySource("classpath:jdbc/jdbc.properties")
@MapperScan(
		  basePackages = "com.example._50_dao.testmapper",
		  sqlSessionFactoryRef = "testSqlSessionFactory"
		)
public class TestJdbcConfig {
			
	/*
	 * Test data source
	 */
	@Qualifier("test")
	@Bean(name="testDataSource")
    public DataSource testDataSource(
    			@Value("${test.datasource.driver-class-name}") String dsDriverClassName,	
    			@Value("${test.datasource.url}") String dsURL,	
    			@Value("${test.datasource.username}") String dsUsername,	
    			@Value("${test.datasource.password}") String dsPassword
    		) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dsDriverClassName);
        dataSource.setUrl(dsURL);
        dataSource.setUsername(dsUsername);
        dataSource.setPassword(dsPassword);
        
        return dataSource;
	}

	/*
	 * One more JdbcTemplate
	 */
	@Qualifier("test")
	@Bean(name="testJdbcTemplate")
	public JdbcTemplate testJdbcTemplate(
			@Qualifier("test") DataSource testDataSource) {
		return new JdbcTemplate(testDataSource);
	}

	/*
	 * SqlSessionFactoryBean for Mybatis
	 */	
	@Qualifier("test")
	@Bean(name="testSqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(
			 @Qualifier("test") DataSource testDataSource) throws Exception {
		  SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		  sessionFactory.setDataSource(testDataSource);
		  return sessionFactory.getObject();
	}
	
	@Qualifier("test")
	@Bean
    public SqlSessionTemplate testSqlSessionTemplate(
    		@Qualifier("test") SqlSessionFactory testSqlSessionFactory) {
         
		return new SqlSessionTemplate(testSqlSessionFactory);
	}

}
