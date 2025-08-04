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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


/*
 * Configure the secondary data source
 */

@Configuration(proxyBeanMethods = false)
@MapperScan(
		  basePackages = "com.example._50_dao.sndmapper",
		  sqlSessionFactoryRef = "sndSqlSessionFactory"
		)
public class SecondaryDataSourceConfig {
			
	/*
	 * Secondary data source
	 */
	@Qualifier("secondary")
	@Bean(defaultCandidate = false)
    public DataSource sndDataSource(
    			@Value("${secondary.datasource.driver-class-name}") String dsDriverClassName,	
    			@Value("${secondary.datasource.url}") String dsURL,	
    			@Value("${secondary.datasource.username}") String dsUsername,	
    			@Value("${secondary.datasource.password}") String dsPassword
    		) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dsDriverClassName);
        dataSource.setUrl(dsURL);
        dataSource.setUsername(dsUsername);
        dataSource.setPassword(dsPassword);
        
        return dataSource;
	}

	/*
	 * JdbcTemplate for the secondary data source
	 */
	@Qualifier("secondary")
	@Bean(defaultCandidate = false)
	public JdbcTemplate sndJdbcTemplate(
			@Qualifier("secondary") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/*
	 * SqlSessionFactoryBean for Mybatis
	 */	
	@Qualifier("secondary")
	@Bean(defaultCandidate = false)
	public SqlSessionFactory sndSqlSessionFactory(
			 @Qualifier("secondary") DataSource dataSource) throws Exception {
		  SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		  sessionFactory.setDataSource(dataSource);
		  return sessionFactory.getObject();
	}
	
	@Qualifier("secondary")
	@Bean(defaultCandidate = false)
    public SqlSessionTemplate sndSqlSessionTemplate(
    		@Qualifier("secondary") SqlSessionFactory sqlSessionFactory) {
         
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
//	@Qualifier("secondary")
//	@Bean(defaultCandidate = false)
//    public SqlSessionFactoryBean sndSqlSessionFactory(
//    		@Qualifier("secondary") DataSource dataSource) {
//		
//		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//		sessionFactoryBean.setDataSource(dataSource);
//		
//		return sessionFactoryBean;
//	}

}
