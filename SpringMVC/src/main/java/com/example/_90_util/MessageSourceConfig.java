/**
 * 
 */
package com.example._90_util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 
 */
@Configuration
public class MessageSourceConfig {
	
	@Bean
	public MessageSource messageSource(
			@Value("${spring.messages.basename}") String msgfile) {
		
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames(msgfile); 
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}

}
