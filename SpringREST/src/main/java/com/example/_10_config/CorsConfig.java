package com.example._10_config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


/**
 * Set up origins which are allowed to call the REST APIs
 * <p>
 * Another option, using @CrossOrigin annotation at class-level, should be able to fulfill 
 * the same function.
 * <br>
 * @author originally sourced from Google.
 * 
 */
@Configuration(proxyBeanMethods = false)
public class CorsConfig {
	
	@Bean
	public CorsWebFilter corsWebFilter() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList(
				"http://localhost:3000",
				"http://localhost:3001",
				"http://localhost:8080")); // Specify allowed origins
		corsConfig.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
		corsConfig.addAllowedHeader("*"); // Allow all headers
		corsConfig.setAllowCredentials(true); // Allow sending credentials (cookies, HTTP authentication)
		corsConfig.setMaxAge(3600L); // How long the pre-flight request can be cached
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig); // Apply this configuration to all paths
		
		return new CorsWebFilter(source);
	}
}
