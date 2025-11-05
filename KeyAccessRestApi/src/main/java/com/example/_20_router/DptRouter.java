/**
 * 
 */
package com.example._20_router;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example._30_service.DptService;
import com.example._50_dto.DptDto;
import com.example._90_util.AppConstant;

import reactor.core.publisher.Mono;

/**
 * 
 */
@Configuration(proxyBeanMethods = false)
public class DptRouter {
	
	@Bean(name = "dptroute")
	public RouterFunction<ServerResponse> route() {

	    return RouterFunctions
	      .route(GET(AppConstant.REST_ROOT + AppConstant.API_DPT_FINDALL)
	    		  .and(accept(MediaType.APPLICATION_JSON)), 
	    		  this::findAllDpts)
	      .andRoute(GET(AppConstant.REST_ROOT + AppConstant.API_DPT_FINDBYID), 
	    		  this::findById)
	      .andRoute(POST(AppConstant.REST_ROOT + AppConstant.API_DPT_INSERT), 
	    		  this::insertDpt)
	      .andRoute(PUT(AppConstant.REST_ROOT + AppConstant.API_DPT_UPDATE), 
	    		  this::updateDpt)
	      .andRoute(DELETE(AppConstant.REST_ROOT + AppConstant.API_DPT_DELETE), 
	    		  this::deleteDpt);
	  }

	
	@Autowired
	DptService dptService;
	
	public Mono<ServerResponse> findAllDpts(ServerRequest request) {
		System.out.println(">> entering " + new Object() {}
			.getClass()
			.getEnclosingMethod()
			.getName() );
		
		List<DptDto> listDpt = dptService.findAllDpts();
		
		listDpt.forEach(dpt -> System.out.println(String.format(">> %s", dpt.toString())));
		
		if (listDpt != null && !ObjectUtils.isEmpty(listDpt)) {
			return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(listDpt));
		} else {
			return ServerResponse.notFound()
					.build();
		}
	}
	
	public Mono<ServerResponse> findById(ServerRequest request){
		
		Map<String, String> pathVariables = request.pathVariables();
		Long dpt_id = Long.parseLong(pathVariables.get("dpt_id")); 
		
		System.out.println(">> findById: %d".formatted(dpt_id));
		List<DptDto> listDpt = dptService.findById(dpt_id);
		
		if (listDpt != null && !ObjectUtils.isEmpty(listDpt)) {
			return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(Mono.just(listDpt.getFirst()), DptDto.class);
		} else {
			return ServerResponse.notFound()
					.build();
		}
	}

	
//	@ResponseStatus(HttpStatus.CREATED)
	public Mono<ServerResponse> insertDpt(ServerRequest request){
		
		return request
				.bodyToMono(DptDto.class)
				.flatMap(dptDto -> {
									
					System.out.println(">> insertDpt: %s".formatted(dptDto.toString()));
					
					dptService.insertDpt(dptDto);
					
			        URI dptURI = URI.create(AppConstant.REST_ROOT
			        		+ String.format(AppConstant.URI_DPT_CREATED, 
			        				dptDto.getDepartment_id()));
			        
			        System.out.println(">> URI: " + dptURI.toString());
			        
			        return ServerResponse.created(dptURI)
			        		.contentType(MediaType.APPLICATION_JSON)
			        		.bodyValue("Department has been added.");
				});
	}
	
	
	public Mono<ServerResponse> updateDpt(ServerRequest request){
		
		return request
				.bodyToMono(DptDto.class)
				.flatMap(dptDto ->{
					
					System.out.println(">> updateDpt: %s".formatted(dptDto.toString()));
					
					dptService.updateDpt(dptDto);

					return ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.bodyValue(new String("Department has been updated."));
				});
	}
	
	
	public Mono<ServerResponse> deleteDpt(ServerRequest request){
		
		Map<String, String> pathVariables = request.pathVariables();
		Long dpt_id = Long.parseLong(pathVariables.get("dpt_id")); 
		
		System.out.println(">> deleteDpt: %d".formatted(dpt_id));
		dptService.deleteDpt(dpt_id);
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(new String("Department has been deleted.")));
	}
}
