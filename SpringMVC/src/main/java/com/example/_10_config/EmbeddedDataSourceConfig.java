package com.example._10_config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/*
 * Configure the data source accessing the embedded database
 */

@Configuration(proxyBeanMethods = false)
@MapperScan(
		  basePackages = "com.example._50_dao.ebdmapper",
		  sqlSessionFactoryRef = "ebdSqlSessionFactory"
		)
public class EmbeddedDataSourceConfig {
	/*
	 * Embedded data source
	 */
	@Qualifier("embedded")
	@Bean(defaultCandidate = false)
	public DataSource ebdDataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.setScriptEncoding("UTF-8")
//				.addScripts("META-INF/sql/ddl.sql", "META-INF/sql/insert-data.sql")
				.build();
	}

	/*
	 * JdbcTemplate for the embedded database
	 */	
	@Qualifier("embedded")
	@Bean(defaultCandidate = false)
	public JdbcTemplate ebdJdbcTemplate(
			@Qualifier("embedded") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	/*
	 * SqlSessionFactoryBean for Mybatis
	 */
	@Qualifier("embedded")
	@Bean(defaultCandidate = false)
	public SqlSessionFactory ebdSqlSessionFactory(
			@Qualifier("embedded") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	 }
	
	@Qualifier("embedded")
	@Bean(defaultCandidate = false)
    public SqlSessionTemplate ebdSqlSessionTemplate(
    		@Qualifier("embedded") SqlSessionFactory ebdSqlSessionFactory) {
         
		return new SqlSessionTemplate(ebdSqlSessionFactory);
	}
}
