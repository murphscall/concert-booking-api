package io.github.murphscall.concertbooking.global.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.github.murphscall.concertbooking.global.annotation.LogExecution;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

	@Around("@annotation(logExecution)")
	public Object logExecution(ProceedingJoinPoint joinPoint, LogExecution logExecution) throws Throwable {
		log.info("예매 요청 시작: {}", joinPoint.getSignature());
		Object result;
		try {
			result = joinPoint.proceed();
			log.info("예매 요청 성공: {}, 반환: {}", joinPoint.getSignature(), result);
		} catch (Throwable t) {
			log.error("메서드 예외: {}", joinPoint.getSignature(), t);
			throw t;
		}
		return result;
	}
}
