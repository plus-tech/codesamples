package com.example._10_config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfig {

//	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
	    http.authorizeHttpRequests(authorize -> {
	      authorize
	        .requestMatchers("/").permitAll()
	        .requestMatchers("/js/**").permitAll()
	        .requestMatchers("/css/**").permitAll()
	        .requestMatchers("/img/**").permitAll()
	        .anyRequest().authenticated();
	    });
	    http.formLogin(form -> form.permitAll());
	    
//	    http.formLogin(form -> {
//	      form.defaultSuccessUrl("/signin");
//	    });
	    
//	    http.formLogin()
//	    		.loginPage("/login")
//	    		.defaultSuccessUrl("/signin");
//	    		.failureUrl();
	    return http.build();
	}
/*
	//
	// Create a user in memory
	@Bean
	public InMemoryUserDetailsManager userDetailsManager(){
	    String username = "user";
	    String password = "pass";

	    UserDetails user = User.withUsername(username)
	      .password(
	        PasswordEncoderFactories
	          .createDelegatingPasswordEncoder()
	          .encode(password))
	      .roles("USER")
	      .build();
	    
	    return new InMemoryUserDetailsManager(user);
	}
*/	
	@Autowired
	@Qualifier("primary")
	private DataSource oracleDataSource;

	@Bean
	public UserDetailsManager userDetailsManager(){
	  JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.oracleDataSource);
	  /*
	  users.createUser(makeUser("user","pass", "USER"));
	  users.createUser(makeUser("admin","pass", "ADMIN"));
	  */	  
	  return users;
	}

	private UserDetails makeUser(String user, String pass, String role) {
	  return User.withUsername(user)
	  .password(
	  PasswordEncoderFactories
	    .createDelegatingPasswordEncoder()
	    .encode(pass))
	    .roles(role)
	    .build();
	}

}
