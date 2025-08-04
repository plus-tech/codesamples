package com.example._10_config;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example._20_dto.DptDto;
import com.example._90_util.AppConstant;


@Component
public class SpringWebClient {
	@Autowired
	Logger Log;

	private final WebClient webClient;


	public SpringWebClient(
			WebClient.Builder builder, 
			@Value("${rest.host}") String restHost) {
		
		this.webClient = builder.baseUrl(restHost).build();
	}

	public Flux<DptDto> findAllDpts() {
		Log.info("--- findAllDpts");
		
		String uri = AppConstant.REST_ROOT + AppConstant.API_DPT_FINDALL;
		
		return this.webClient
				.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(status -> status.equals(HttpStatus.NOT_FOUND), 
		                  clientResponse -> Mono.error(new Exception("Departments not found")))
				.bodyToFlux(DptDto.class)
				.onErrorResume(Exception.class, e -> {
		            return Flux.empty(); 
		        });
	}
	

	public Mono<DptDto> findById(Long department_id) {
		Log.info("--- findById : " + Long.toString(department_id));
		
		String uri = AppConstant.REST_ROOT + AppConstant.API_DPT_FINDBYID + Long.toString(department_id);
		
		return this.webClient
				.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(status -> status.equals(HttpStatus.NOT_FOUND), 
		                  clientResponse -> Mono.error(new Exception("Department not found")))
				.bodyToMono(DptDto.class)
				.onErrorResume(Exception.class, e -> {
		            return Mono.empty(); 
		        });
	}

	public Mono<String> insertDpt(DptDto dptDto) {
		Log.info("--- insertDpt : " + dptDto.toString());
		
		String uri = AppConstant.REST_ROOT + AppConstant.API_DPT_INSERT;
			
		return this.webClient
				.post()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(dptDto)
				.retrieve()
				.bodyToMono(String.class);
	}
	
	public Mono<String> updateDpt(DptDto dptDto) {
		Log.info("--- updateDpt : " + dptDto.toString());
		
		String uri = AppConstant.REST_ROOT + AppConstant.API_DPT_UPDATE;
			
		return this.webClient
				.put()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(dptDto)
				.retrieve()
				.bodyToMono(String.class);
	}
	
	public Mono<String> deleteDpt(Long department_id){
		Log.info("--- deleteDpt : " + Long.toString(department_id));
		
		String uri = AppConstant.REST_ROOT + AppConstant.API_DPT_DELETE + Long.toString(department_id);
		
		return this.webClient
				.delete()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class);
	}
  
}
