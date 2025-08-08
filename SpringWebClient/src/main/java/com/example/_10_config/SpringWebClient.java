package com.example._10_config;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example._20_dto.DptDto;
import com.example._90_util.AppConstant;


@Component
public class SpringWebClient {
	@Autowired
	Logger log;

	private final WebClient webClient;


	public SpringWebClient(
			WebClient.Builder builder, 
			@Value("${rest.host}") String restHost) {
		
		this.webClient = builder.baseUrl(restHost).build();
	}

	public Flux<DptDto> findAllDpts() {
		log.info(">> findAllDpts");
		
		String uri = AppConstant.REST_ROOT + AppConstant.API_DPT_FINDALL;
		
		return this.webClient
				.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(status -> status.equals(HttpStatus.NOT_FOUND), 
						clientResponse -> Mono.error(
								new Exception("Departments not found")))
				.bodyToFlux(DptDto.class)
				.onErrorResume(Exception.class, 
						e -> {return Flux.empty();});
	}
	

	public Mono<DptDto> findById(Long department_id) {
		log.info(">> findById : " + Long.toString(department_id));
		
		String uri = AppConstant.REST_ROOT + AppConstant.API_DPT_FINDBYID 
				+ Long.toString(department_id);
		
		return this.webClient
				.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(status -> status.equals(HttpStatus.NOT_FOUND), 
						clientResponse -> Mono.error(
								new RuntimeException("Department not found")))
				.bodyToMono(DptDto.class)
				.onErrorResume(RuntimeException.class, 
						e -> {return Mono.empty();});
	}

	public Mono<URI> insertDpt(DptDto dptDto) {
		log.info(">> insertDpt : " + dptDto.toString());
		
		String uri = AppConstant.REST_ROOT + AppConstant.API_DPT_INSERT;
		
		return webClient.post()
		.uri(uri)
		.bodyValue(dptDto)
		.accept(MediaType.APPLICATION_JSON)
		.exchangeToMono(response -> {
			
			if (response.statusCode().equals(HttpStatus.CREATED)) {
				HttpHeaders headers = response.headers().asHttpHeaders();
				URI locHeader = headers.getLocation();
				
				if (locHeader != null) {
					return Mono.just(locHeader);
				} else {
					return Mono.error(new IllegalStateException(
							"URI of the newly created department is not found."));
				}
			} else {
				return Mono.error(new RuntimeException(
						"An error occurred while creating a new department."));
			}
		});

//		.retrieve()
//		.toEntity(String.class)
//		.subscribe(response -> {
//			HttpHeaders header = response.getHeaders();
//			URI locHeader = header.getLocation();
//			System.out.println(">> returned URI: " + locHeader);
//		}
	}
	
	public Mono<String> updateDpt(DptDto dptDto) {
		log.info(">> updateDpt : " + dptDto.toString());
		
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
		log.info(">> deleteDpt : " + Long.toString(department_id));
		
		String uri = AppConstant.REST_ROOT + AppConstant.API_DPT_DELETE + Long.toString(department_id);
		
		return this.webClient
				.delete()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class);
	}
  
}
