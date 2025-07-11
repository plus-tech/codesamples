/**
 * 
 */
package com.example._90_util;

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
    public PlatformTransactionManager primaryTransactionManager(DataSource dataSourceOracle) {
        return new DataSourceTransactionManager(dataSourceOracle);
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(DataSource dataSourceMore) {
        return new DataSourceTransactionManager(dataSourceMore);
    }
    
    @Bean(name = "embeddedTransactionManager")
    public PlatformTransactionManager embeddedTransactionManager(DataSource dataSourceEmbedded) {
        return new DataSourceTransactionManager(dataSourceEmbedded);
    }

}
