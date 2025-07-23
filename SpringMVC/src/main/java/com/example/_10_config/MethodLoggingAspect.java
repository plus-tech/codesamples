package com.example._10_config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

@Aspect
@Component
public class MethodLoggingAspect {
	@Autowired
	private Logger log;

	/*
	 * all methods on all beans in all packages under "com.example._20_controller"
	 */
	@Before("execution(* com.example._20_controller..*.*(..))")
	public void startLogging(JoinPoint jp) {
		log.info(">> entering " + jp.getSignature());
	}
	
	/*
	 * all methods on all beans in all packages under "com.example._20_controller"
	 */
	@AfterReturning(pointcut="execution(* com.example._20_controller..*.*(..))")
	public void finishLogging(JoinPoint jp) {
		log.info(">> leaving " + jp.getSignature());
	}
	
}
