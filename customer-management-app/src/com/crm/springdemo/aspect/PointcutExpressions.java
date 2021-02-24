package com.crm.springdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointcutExpressions {
	
	@Pointcut("execution(* com.crm.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.crm.springdemo.dao.*.*(..))")
	private void forDAOPackage() {}
	
	@Pointcut("execution(* com.crm.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("forControllerPackage() || forDAOPackage() || forServicePackage()")
	public void forAppFlow() {}
}