package com.example._20_router;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.slf4j.Logger;

import com.example._90_util.AppConstant;

import reactor.core.publisher.Mono;


@Configuration(proxyBeanMethods = false)
public class AppUtilRouter {
	
	@Bean(name = "apputilroute")
	public RouterFunction<ServerResponse> route() {

		return RouterFunctions
				.route(GET(AppConstant.APPUTIL_ROOT + AppConstant.API_GEN_KEY)
				.and(accept(MediaType.APPLICATION_JSON)), this::generateAesKey);
	}
	
	public Mono<ServerResponse> generateAesKey(ServerRequest serverRequest) {
		SecretKey  apiKey = null;
		
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(256); // 256-bit AES key
			apiKey = keyGen.generateKey();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		System.out.println(">> api key: %s".formatted(apiKey.toString()));
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(apiKey==null? "failed to generate an access key.":apiKey);
	}
}
