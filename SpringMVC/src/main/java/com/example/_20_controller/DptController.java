package com.example._20_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example._30_service.DptService;
import com.example._60_dto.DptDto;
import com.example._90_util.AppConstant;



@Controller
public class DptController{
	
	@Autowired
	private DptService dptService;
	
	@Autowired
	private MessageSource msgSource;

	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping(AppConstant.PATH_DPT_FINDALL)
	public ModelAndView findAllDpts(ModelAndView mav) {
		String appHead = msgSource.getMessage(AppConstant.APP_HEAD, null, null);
		
		List<DptDto> dptlist = dptService.findAll();
		System.out.println(String.format(">> find all departments: %s", dptlist.toString()));
		
	    mav.setViewName("departments");
	    mav.addObject("msg", appHead);
	    mav.addObject("departments", dptlist);
	    
	    return mav;
	}
	
	@GetMapping(AppConstant.PATH_DPT_FINDBYID)
	public ModelAndView findById(
			@PathVariable Long department_id,
			ModelAndView mav) {
		
		System.out.println(String.format(">> find by id: ", department_id));
		
		List<DptDto> dptList = dptService.findById(department_id);
		
	    mav.setViewName("departments");
	    mav.addObject("departments", dptList);
	    
	    return mav;	
	}
	
//	@PostMapping("/insertDpt")
	@RequestMapping(	value = AppConstant.PATH_DPT_INSERT, method = RequestMethod.POST)
	public ModelAndView insertDpt(
			@ModelAttribute("dptDto") DptDto dptDto,
			ModelAndView mav	) {
		
		System.out.println(">> inserting department %s".formatted(dptDto.toString()));
		dptService.insertDpt(dptDto);
		
		List<DptDto> dptlist = dptService.findAll();
		mav.addObject("departments", dptlist);
		mav.setViewName("departments");
		return mav;
	}

	@PutMapping(AppConstant.PATH_DPT_UPDATE)
	public ModelAndView updateDpt(
			@ModelAttribute("dptDto") DptDto dptDto,
			ModelAndView mav	){
		
		System.out.println(String.format(">> updating department %s", dptDto.toString()));
		dptService.updateDpt(dptDto);
		
		List<DptDto> dptlist = dptService.findAll();
		mav.addObject("departments", dptlist);
		mav.setViewName("departments");
		return mav;
	}
	
	@DeleteMapping(AppConstant.PATH_DPT_DELETE)
	public ModelAndView deleteDpt(
			@PathVariable Long department_id,
			ModelAndView mav){
		
		System.out.println(String.format(">> deleting department %d",department_id));
		
		dptService.deleteDpt(department_id);
		
		List<DptDto> dptlist = dptService.findAll();
		mav.addObject("departments", dptlist);
		mav.setViewName("departments");
		return mav;
	}
}
