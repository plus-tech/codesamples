package com.example._20_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example._30_service.DptService;
import com.example._60_dto.DptDto;
import com.example._90_util.AppConstant;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping(AppConstant.REST_ROOT)
public class DptRestController {
	
	@Autowired
	Logger log;
	
	@Autowired
	DptService dptService;
	
	@Autowired
	MessageSource msgSource;
	
	
	@GetMapping(AppConstant.PATH_DPT_FINDALL)
	@ResponseBody
	@Operation(method="GET", description="Get the list of all the departments")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200", description="Department found"),
  		@ApiResponse(responseCode="404", description="Something wrong in the request"),
  		@ApiResponse(responseCode="500", description="An internal server error occurred")})
	public List<DptDto> findAllDpts() {
		
		List<DptDto> dptList = dptService.findAll();
		
		log.info(dptList.toString());
		    
	    return dptList;
	}
	
	@GetMapping(AppConstant.PATH_DPT_FINDBYID)
	@ResponseBody
	@Operation(method="GET", description="Get the department with the specified department id")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200", description="Department found"),
  		@ApiResponse(responseCode="404", description="Something wrong in the request"),
  		@ApiResponse(responseCode="500", description="An internal server error occurred")})
	public DptDto findById(
			@PathVariable Long department_id) {
		
		List<DptDto> dptList = dptService.findById(department_id);
		
		log.info(dptList.toString());
		
		return (ObjectUtils.isEmpty(dptList)? null: dptList.getFirst());
	}
	
	@PostMapping(AppConstant.PATH_DPT_INSERT)
	@ResponseStatus(HttpStatus.CREATED)
	public void insertDpt(
			@RequestBody DptDto dptDto){
		
		log.info(dptDto.toString());
		
		dptService.insertDpt(dptDto);
	}
	
	@PutMapping(AppConstant.PATH_DPT_UPDATE)
	public void updateDpt(
			@RequestBody DptDto dptDto){
		
		log.info(dptDto.toString());
		
		dptService.updateDpt(dptDto);
	}
	
	@DeleteMapping(AppConstant.PATH_DPT_DELETE)
	public void deleteDpt(@PathVariable Long department_id){
		
		log.info(String.format("deleting department %d",department_id));
		
		dptService.deleteDpt(department_id);		
	}

}
