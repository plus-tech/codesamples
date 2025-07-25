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
	
    @Bean(name = "sndTransactionManager", defaultCandidate = false)
    public PlatformTransactionManager sndTransactionManager(
    		@Qualifier("secondary") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean(name = "ebdTransactionManager", defaultCandidate = false)
    public PlatformTransactionManager ebdTransactionManager(
    		@Qualifier("embedded") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
