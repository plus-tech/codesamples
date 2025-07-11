package com.example._20_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example._90_util.AppConstant;

import ch.qos.logback.core.joran.spi.HttpUtil.RequestMethod;

@Controller
public class SigninController {
	
//	@RequestMapping("/")
//	public ModelAndView index(ModelAndView mav) {
//		mav.setViewName(AppConstant.VIEW_INDEX);
//		
//		return mav;
//	}
	
//	@GetMapping("/login")
//	public String login() {
//		return AppConstant.VIEW_LOGIN;
//	}
	
//	@GetMapping("/signin")
////	@RequestMapping(value = "/signin", method = RequestMethod.POST)
//	public String signin() {
//		return AppConstant.VIEW_MAINPAGE;
//	}
}
