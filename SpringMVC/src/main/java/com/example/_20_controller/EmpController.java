package com.example._20_controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example._30_service.DptService;
import com.example._30_service.EmpService;
import com.example._60_dto.EmpDptDto;
import com.example._60_dto.EmpDto;
import com.example._90_util.AppConstant;



@Controller
public class EmpController{
	
	@Autowired
	private EmpService empService;
	
	@Autowired
	private MessageSource msgSource;
	
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/findallemps")
	public ModelAndView findAllEmps(ModelAndView mav) {
		String appHead = msgSource.getMessage(AppConstant.APP_HEAD, null, null);
		
		List<EmpDto> emplist = empService.findAll();
//		System.out.println(emplist);
		
		//
		// test : left join
		List<Map<String, Object>> leftjoin = empService.leftJoin();
		System.out.println(leftjoin);
		
		// test
		List<EmpDptDto> empdptList = empService.empLeftJoinDpt();
		System.out.println(empdptList);
		
	    mav.setViewName("employees");
	    mav.addObject("msg", appHead);
	    mav.addObject("employees", emplist);
	    
	    return mav;
	}

}
