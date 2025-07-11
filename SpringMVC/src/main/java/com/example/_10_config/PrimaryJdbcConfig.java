package com.example._10_config;

import javax.sql.DataSource;

//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


/*
 * Create a customized DataSource
 */

@Configuration(proxyBeanMethods = false)
//@PropertySource("classpath:jdbc/jdbc.properties")
@MapperScan(
		  basePackages = "com.example._50_dao.mapper",
		  sqlSessionFactoryRef = "primarySqlSessionFactory"
		)
public class PrimaryJdbcConfig {
			
	/*
	 * Oracle data source
	 */
//	@Bean(destroyMethod="close")
	@Primary
	@Bean(name="dataSourceOracle")
    public DataSource dataSourceOracle(
    			@Value("${spring.datasource.driver-class-name}") String dsDriverClassName,
    			@Value("${spring.datasource.url}") String dsURL,
    			@Value("${spring.datasource.username}") String dsUsername,
    			@Value("${spring.datasource.password}") String dsPassword
    		) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dsDriverClassName);
        dataSource.setUrl(dsURL);
        dataSource.setUsername(dsUsername);
        dataSource.setPassword(dsPassword);
        
        return dataSource;
	}
	
	/*
	 * JdbcTemplate for Oracle data source
	 */
	@Primary
	@Bean(name="jdbcTemplateOracle")
	public JdbcTemplate jdbcTemplateOracle(
			@Qualifier("dataSourceOracle") DataSource dataSourceOracle) {
		return new JdbcTemplate(dataSourceOracle);
	}
	
	/*
	 * SqlSessionFactoryBean for Mybatis
	 */
	@Primary
	@Bean("primarySqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory(
			@Qualifier("dataSourceOracle") DataSource dataSourceOracle) {
		
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSourceOracle);
//		sessionFactoryBean.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
		
		return sessionFactoryBean;
	}

}
