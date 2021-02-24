package com.crm.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Before("com.crm.springdemo.aspect.PointcutExpressions.forAppFlow()")
	public void beforeAdvice(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		
		myLogger.info(" =====>> @Before advice called in : " + method);
		
		Object[] args = joinPoint.getArgs();
		
		for (Object tempArg : args) {
			myLogger.info(" =====>> arguments : " + tempArg);
		}
	}
	
	@AfterReturning(
			pointcut="com.crm.springdemo.aspect.PointcutExpressions.forAppFlow()",
			returning="result"
			)
	public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
		String method = joinPoint.getSignature().toShortString();
		
		myLogger.info(" =====>> @AfterReturning advice called in : " + method);
		
		myLogger.info(" =====>> result : " + result);
	}
}
