package com.example._20_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/findById/{dpt_id}")
	public ModelAndView findById(
			@PathVariable Long dpt_id,
			ModelAndView mav) {
		
		System.out.println("-------- controller:");
		System.out.println(dpt_id);
		
		List<DptDto> dptList = dptService.findById(dpt_id);
		
	    mav.setViewName("departments");
	    mav.addObject("departments", dptList);
	    
	    return mav;	
	}
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/findalldpts")
	public ModelAndView findalldpts(ModelAndView mav) {
		String appHead = msgSource.getMessage(AppConstant.APP_HEAD, null, null);
		
		List<DptDto> dptlist = dptService.getAll();
		
	    mav.setViewName("departments");
	    mav.addObject("msg", appHead);
	    mav.addObject("departments", dptlist);
	    
	    return mav;
	}
	
//	@PostMapping("/insertDpt")
	@RequestMapping(value = "/insertDpt", method = RequestMethod.POST)
	public ModelAndView addDpt(
			@ModelAttribute("dptDto") DptDto dptDto,
			ModelAndView mav	) {
		
		System.out.println("Clicked");
		System.out.println(dptDto.toString());
		dptService.insertDpt(dptDto);
		
		List<DptDto> dptlist = dptService.getAll();
		mav.addObject("departments", dptlist);
		mav.setViewName("departments");
		return mav;
	}

}
