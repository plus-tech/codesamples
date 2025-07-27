package com.example._20_router;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;

import com.example._30_service.DptService;
import com.example._50_dto.DptDto;
import com.example._90_util.AppConstant;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Configuration(proxyBeanMethods = false)
@CrossOrigin(
	origins = {"http://localhost:3000", "http://localhost:8080"}, 
	methods = {
			RequestMethod.GET, RequestMethod.POST, 
			RequestMethod.PUT, RequestMethod.DELETE}
) // Class-level
public class DptRouter {
	
	@Bean
	public RouterFunction<ServerResponse> route() {

	    return RouterFunctions
	      .route(GET(AppConstant.API_ROOT+AppConstant.REST_DPT_FINDALL).and(accept(MediaType.APPLICATION_JSON)), this::findAllDpts)
	      .andRoute(GET(AppConstant.API_ROOT+AppConstant.REST_DPT_FINDBYID), this::findById)
	      .andRoute(POST(AppConstant.API_ROOT+AppConstant.REST_DPT_INSERT), this::insertDpt)
	      .andRoute(PUT(AppConstant.API_ROOT+AppConstant.REST_DPT_UPDATE), this::updateDpt)
	      .andRoute(DELETE(AppConstant.API_ROOT+AppConstant.REST_DPT_DELETE), this::deleteDpt);
	  }

	
	@Autowired
	Logger Log;
	
	@Autowired
	private DptService dptService;
	
	public Mono<ServerResponse> findAllDpts(ServerRequest request) {
		
		List<DptDto> listDpt = dptService.findAll();
		
		listDpt.forEach(dpt -> Log.info(String.format("--- %s", dpt.toString())));
		
		if (!ObjectUtils.isEmpty(listDpt)) {
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
		Long department_id = Long.parseLong(pathVariables.get("department_id")); 
		
		Log.info("--- findById: %d".formatted(department_id));
		List<DptDto> listDpt = dptService.findById(department_id);
		
		if (!ObjectUtils.isEmpty(listDpt)) {

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
									
					Log.info("--- insertDpt: %s".formatted(dptDto.toString()));
					
					dptService.insertDpt(dptDto);
					
					return ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.bodyValue("Department has been added.");
				});

		/*
		DptDto monodptDto = request.formData()
				.flatMap(formData -> {
					
					Long dptId = Long.parseLong(formData.getFirst("department_id"));
					String dptName = formData.getFirst("department_name");					
					Integer mgrId = Integer.parseInt(formData.getFirst("manager_id"));
					
					DptDto tmpdptDto = new DptDto(dptId, dptName, mgrId);
					
					return Mono.just(tmpdptDto);
				});
		*/
	}
	
	
	public Mono<ServerResponse> updateDpt(ServerRequest request){
		
		return request
				.bodyToMono(DptDto.class)
				.flatMap(dptDto ->{
					
					Log.info("--- updateDpt: %s".formatted(dptDto.toString()));
					
					dptService.updateDpt(dptDto);

					return ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.bodyValue(new String("Department has been updated."));
				});
	}
	
	
	public Mono<ServerResponse> deleteDpt(ServerRequest request){
		
		Map<String, String> pathVariables = request.pathVariables();
		Long department_id = Long.parseLong(pathVariables.get("department_id")); 
		
		Log.info("--- deleteDpt: %d".formatted(department_id));
		dptService.deleteDpt(department_id);
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(new String("Department has been deleted.")));

	}
}
