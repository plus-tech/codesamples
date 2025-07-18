package com.example._10_config;

import javax.sql.DataSource;

//import org.springframework.boot.context.properties.ConfigurationProperties;
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
 * Create a customized DataSource
 */

@Configuration(proxyBeanMethods = false)
//@PropertySource("classpath:jdbc/jdbc.properties")
@MapperScan(
		  basePackages = "com.example._50_dao.embeddedmapper",
		  sqlSessionFactoryRef = "embeddedSqlSessionFactory"
		)
public class EmbeddedJdbcConfig {
	/*
	 * Embedded data source
	 */
	@Qualifier("embedded")
	@Bean(name="embeddedDataSource")
	public DataSource embeddedDataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.setScriptEncoding("UTF-8")
//				.addScripts("META-INF/sql/ddl.sql", "META-INF/sql/insert-data.sql")
				.build();
	}

	/*
	 * JdbcTemplate for the Embedded database
	 */	
	@Qualifier("embedded")
	@Bean(name="embeddedJdbcTemplate")
	public JdbcTemplate embeddedJdbcTemplate(
			@Qualifier("embedded") DataSource dataSourceEmbedded) {
		return new JdbcTemplate(dataSourceEmbedded);
	}
	
	/*
	 * SqlSessionFactoryBean for Mybatis
	 */
	@Qualifier("embedded")
	@Bean("embeddedSqlSessionFactory")
	public SqlSessionFactory embeddedSqlSessionFactory(
			@Qualifier("embedded") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	 }
	
	@Qualifier("embedded")
	@Bean("embeddedSqlSessionTemplate")
    public SqlSessionTemplate embeddedSqlSessionTemplate(
    		@Qualifier("embedded") SqlSessionFactory embeddedSqlSessionFactory) {
         
		return new SqlSessionTemplate(embeddedSqlSessionFactory);
	}
}
