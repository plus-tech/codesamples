package com.example._40_processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import com.example._70_dto.User;

@Configuration
public class DbToDbBatchProcessor {

	@Qualifier("bcp")
	@Bean(defaultCandidate=false)
	@StepScope
	public ItemProcessor<User, User> dtdImportUserStepProcessor() {
		return item -> {
		    	 /*
		    	  * Transforming data if necessary
		    	  */
			item.setUsername(item.getUsername());
			item.setPassword(item.getPassword());
			item.setRole(item.getRole());
			item.setEnabled(item.getEnabled());
			
			return item;
		};
	}
	
}
