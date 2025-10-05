package io.github.murphscall.concertbooking.global.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import io.github.murphscall.concertbooking.global.annotation.DistributedLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class DistributedLockAspect {

	private final RedissonClient redissonClient;
	private final AopForTransaction aopForTransaction;
	private final ExpressionParser parser = new SpelExpressionParser();
	private final ParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

	@Around("@annotation(distributedLock)")
	public Object lock(final ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {

		// AOP 가 적용된 지점, 그 지점에서 실행되는 메소드나 생성자의 시그니처를 받아온다.
		Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();

		// 메소드의 매개변수 이름을 가져온다.
		String[] paramNames = nameDiscoverer.getParameterNames(method);
		Object[] args = joinPoint.getArgs();

		StandardEvaluationContext context = new StandardEvaluationContext();
		for (int i = 0; i < paramNames.length; i++) {
			context.setVariable(paramNames[i], args[i]);
		}

		String key = parser.parseExpression(distributedLock.key()).getValue(context, String.class);
		RLock lock = redissonClient.getLock(key);

		try {
			boolean isLocked = lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(),
				distributedLock.timeUnit());
			if (!isLocked) {
				throw new InterruptedException("락을 획득 할 수 없습니다.");
			}

			log.info("락 획득 성공 : {}", key);
			return aopForTransaction.proceed(joinPoint);
		} catch (InterruptedException e) {
			throw new InterruptedException();
		} finally {
			try {
				lock.unlock();
				log.info("[LOCK] 락 해제 완료: {}", key);
			} catch (IllegalMonitorStateException e) {
				log.info("[LOCK] 이미 해제된 락: {}", key);
			}
		}
	}
}
