package com.example._10_config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;


/*
 * Disable data source auto-configuration with "exclude" configuration option
 *   @SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
 *   
 * Define a data source in an explicit way
 * If auto-configuration enabled, this configuration below can be removed.
 */

@Configuration(proxyBeanMethods = false)
@MapperScan(
		  basePackages = "com.example._50_dao.mapper",
		  sqlSessionFactoryRef = "sqlSessionFactory"
		)
public class PrimaryDataSourceConfig {
			
	/*
	 * Primary data source
	 */
	@Primary
	@Bean
    public DataSource dataSource(
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
	 * JdbcTemplate for primary data source
	 */
	@Primary
	@Bean
	public JdbcTemplate jdbcTemplate(
			DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
    		DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
	/*
	 * SqlSessionFactoryBean for Mybatis
	 */
	@Primary
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(
			DataSource dataSource) {
		
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
//		sessionFactoryBean.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
		
		return sessionFactoryBean;
	}

}
