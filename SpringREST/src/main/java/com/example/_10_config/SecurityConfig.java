package com.example._10_config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.example._40_dao.UsersDao;


@Configuration
//@EnableWebFluxSecurity
public class SecurityConfig {

/*
	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http
			.cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS for Spring Security
			.authorizeExchange(exchanges -> exchanges
			    .anyExchange().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			.formLogin(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.addAllowedOriginPattern("*"); // addAllowedOrigin("http://localhost:3000"); 
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}		

	@Autowired
	UsersDao usersDao;
	
	@Bean
	public MapReactiveUserDetailsService userDetailsService() {
			
//		UserDetails user = User.withUsername("user")
//			.password(PasswordEncoderFactories
//			          .createDelegatingPasswordEncoder()
//			          .encode("pass"))
//			.roles("USER")
//			.build();
//		return new MapReactiveUserDetailsService(user);
		
		List<UserDetails> listUsers = usersDao.findAllUsers();
		
		return new MapReactiveUserDetailsService(listUsers);
	}
*/
}
