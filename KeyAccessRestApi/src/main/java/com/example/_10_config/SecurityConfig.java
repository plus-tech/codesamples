package com.example._10_config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.WebFilter;

import com.example._40_dao.ApiKeyDaoImpl;
import com.example._90_util.AppConstant;

import reactor.core.publisher.Mono;
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
@EnableWebFluxSecurity
public class SecurityConfig {
	
	@Autowired
	private ApiKeyDaoImpl apiKeyDao;
	
//	This didn't work after the custom filter was added in which processes the access key
//	
//	@Bean
//	public CorsWebFilter corsWebFilter() {
//		System.out.println(">> entering corsWebFilter");
//		
//		CorsConfiguration corsConfig = new CorsConfiguration();
//		corsConfig.setAllowedOrigins(Arrays.asList(
//				"http://localhost:3000",
//				"http://10.64.104.109:3000",
//				"http://localhost:3001",
//				"http://localhost:8080")); // Specify allowed origins
//		corsConfig.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
//		corsConfig.addAllowedHeader("*"); // Allow all headers
//		corsConfig.setAllowCredentials(true); // Allow sending credentials (cookies, HTTP authentication)
//		corsConfig.setMaxAge(3600L); // How long the pre-flight request can be cached
//		
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", corsConfig); // Apply this configuration to all paths
//		
//		return new CorsWebFilter(source);
//	}

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Allow all origins for example
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		AuthenticationWebFilter authenticationWebFilter = 
			new AuthenticationWebFilter(reactiveAuthenticationManager());
		authenticationWebFilter.setServerAuthenticationConverter(serverAuthenticationConverter());

//        // Define a custom WebFilter
//        WebFilter customFilter = (exchange, chain) -> {
//            System.out.println("Custom Filter executed at a specific position!");
//            // Perform custom logic here
//            return chain.filter(exchange);
//        };
        
		http.csrf(ServerHttpSecurity.CsrfSpec::disable) // Disable CSRF for API key based auth
				.authorizeExchange(exchanges -> exchanges
                        .pathMatchers(AppConstant.APPUTIL_ROOT + "/**").permitAll() // App Utility endpoint
                        .anyExchange().authenticated() // All other endpoints require authentication
				)
				.addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
				.httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
				.formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                ;        
        return http.build();
    }

	@Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
    		System.out.println(">> entering reactiveAuthenticationManager");
    		
    		//
    		// Get all the valid keys from the database
    		List<String> validApiKeys = apiKeyDao.getApiKeys();
    		
    		return authentication -> {
    			String apiKey = authentication.getCredentials().toString();
    			if (validApiKeys.contains(apiKey)) {
    				return Mono.just(new UsernamePasswordAuthenticationToken(apiKey, apiKey,
    						Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));
    			}
    			return Mono.error(new BadCredentialsException("Invalid API Key"));
    		};
	}

    @Bean
    public ServerAuthenticationConverter serverAuthenticationConverter() {
    		System.out.println(">> entering serverAuthenticationConverter");
    		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(AppConstant.API_KEY_HEADER))
    				.map(apiKey -> new UsernamePasswordAuthenticationToken(apiKey, apiKey));
    }

}
