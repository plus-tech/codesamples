package com.example._40_processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import com.example._70_dto.User;

@Configuration
public class OrganizedBatchProcessor {


	@Bean(name="obImportFileStepProcessor")
	@StepScope
	public ItemProcessor<User, User> obImportFileStepProcessor() {
		return item -> {
		    	 /*
		    	  * Transforming data: encrypt the password
		    	  */
			item.setUsername(item.getUsername());
			item.setPassword(
					PasswordEncoderFactories
					.createDelegatingPasswordEncoder()
					.encode(item.getPassword()));
			item.setRole("ROLE_"+item.getRole());
			
			return item;
		};
	}
	
}
