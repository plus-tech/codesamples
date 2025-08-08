package com.example._20_router;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example._30_service.EmpService;
import com.example._50_dto.DptDto;
import com.example._50_dto.EmpDto;
import com.example._90_util.AppConstant;

import reactor.core.publisher.Mono;


@Configuration(proxyBeanMethods = false)
public class EmpRouter {
	
	@Bean(name = "emproute")
	public RouterFunction<ServerResponse> route() {

	    return RouterFunctions
	      .route(GET(AppConstant.REST_ROOT+AppConstant.API_EMP_FINDALL)
	    		  .and(accept(MediaType.APPLICATION_JSON)), 
	    		  this::findAllEmps)
	      .andRoute(GET(AppConstant.REST_ROOT+AppConstant.API_EMP_FINDBYID)
	    		  .and(accept(MediaType.APPLICATION_JSON)), 
	    		  this::findById)
	      .andRoute(POST(AppConstant.REST_ROOT+AppConstant.API_EMP_INSERT)
	    		  .and(accept(MediaType.APPLICATION_JSON)),
	    		  this::insertEmp)
	      .andRoute(PUT(AppConstant.REST_ROOT+AppConstant.API_EMP_UPDATE)
	    		  .and(accept(MediaType.APPLICATION_JSON)), 
	    		  this::updateEmp)
	      .andRoute(DELETE(AppConstant.REST_ROOT+AppConstant.API_EMP_DELETE)
	    		  .and(accept(MediaType.APPLICATION_JSON)), 
	    		  this::deleteEmp);	    
	}
	
	@Autowired
	Logger log;
	
	@Autowired
	EmpService empService;
	
	public Mono<ServerResponse> findAllEmps(ServerRequest request){
		
		List<EmpDto> listEmp = empService.findAll();
		
		if (!ObjectUtils.isEmpty(listEmp)) {
			return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(listEmp));
		} else {
			return ServerResponse.notFound()
					.build();
		}
	}

	public Mono<ServerResponse> findById(ServerRequest request){
	
		Map<String, String> pathVariables = request.pathVariables();
		Integer employee_id = Integer.parseInt(pathVariables.get("employee_id")); 

		List<EmpDto> listEmp = empService.findById(employee_id);
		
		if (!ObjectUtils.isEmpty(listEmp)) {
			return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(listEmp.getFirst()));
					//.body(Mono.just(listDpt.getFirst()), DptDto.class);
		} else {
			return ServerResponse.notFound()
					.build();
		}
	}
	
	
	public Mono<ServerResponse> insertEmp(ServerRequest request){
		
		return request
				.bodyToMono(EmpDto.class)
				.flatMap(empDto -> {
									
					log.info(">> insertEmp: %s".formatted(empDto.toString()));
					
					empService.insertEmp(empDto);
					
			        URI empURI = URI.create(AppConstant.REST_ROOT
			        		+ String.format(AppConstant.URI_EMP_CREATED, 
			        				empDto.getEmployee_id()));
			        
			        return ServerResponse.created(empURI)
					        	.contentType(MediaType.APPLICATION_JSON)
					        	.bodyValue("Employee has been added.");
				});
	}
	
	public Mono<ServerResponse> updateEmp(ServerRequest request){
		
		return request
				.bodyToMono(EmpDto.class)
				.flatMap(empDto -> {
									
					log.info(">> updatetEmp: %s".formatted(empDto.toString()));
					
					empService.updateEmp(empDto);
					
					return ServerResponse.status(HttpStatus.OK)
							.contentType(MediaType.APPLICATION_JSON)
							.bodyValue("Employee has been updated.");
				});
	}
	
	public Mono<ServerResponse> deleteEmp(ServerRequest request){
		Map<String, String> pathVariables = request.pathVariables();
		Integer employee_id = Integer.parseInt(pathVariables.get("employee_id")); 
		
		empService.deleteEmp(employee_id);
		
		return ServerResponse.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue("Employee has been deleted.");
	}
}
