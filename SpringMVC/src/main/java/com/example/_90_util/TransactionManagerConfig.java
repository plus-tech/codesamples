/**
 * 
 */
package com.example._90_util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
    

/**
 * 
 */
@Configuration
public class TransactionManagerConfig {
	
	@Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
    		@Qualifier("primary") DataSource oracleDataSource) {
        return new DataSourceTransactionManager(oracleDataSource);
    }

    @Bean(name = "testTransactionManager")
    public PlatformTransactionManager testTransactionManager(
    		@Qualifier("test") DataSource testDataSource) {
        return new DataSourceTransactionManager(testDataSource);
    }
    
    @Bean(name = "embeddedTransactionManager")
    public PlatformTransactionManager embeddedTransactionManager(
    		@Qualifier("embedded") DataSource embeddedDataSource) {
        return new DataSourceTransactionManager(embeddedDataSource);
    }

}
