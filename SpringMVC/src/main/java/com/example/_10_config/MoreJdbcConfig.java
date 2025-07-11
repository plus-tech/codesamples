package com.example._10_config;

import javax.sql.DataSource;

//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
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
		  basePackages = "com.example._50_dao.moremapper",
		  sqlSessionFactoryRef = "moreSqlSessionFactory"
		)
public class MoreJdbcConfig {
			
	/*
	 * One more data source
	 */
	@Bean(name="dataSourceMore")
    public DataSource dataSourceMore(
    			@Value("${more.datasource.driver-class-name}") String dsDriverClassName,	
    			@Value("${more.datasource.url}") String dsURL,	
    			@Value("${more.datasource.username}") String dsUsername,	
    			@Value("${more.datasource.password}") String dsPassword
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
	@Bean(name="jdbcTemplateMore")
	public JdbcTemplate jdbcTemplateMore(
			@Qualifier("dataSourceMore") DataSource dataSourceMore) {
		return new JdbcTemplate(dataSourceMore);
	}

	/*
	 * SqlSessionFactoryBean for Mybatis
	 */	
	@Bean("moreSqlSessionFactory")
	 public SqlSessionFactory sqlSessionFactory(
			 @Qualifier("dataSourceMore") DataSource dataSource) throws Exception {
		  SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		  sessionFactory.setDataSource(dataSource);
		  return sessionFactory.getObject();
	 }

}
