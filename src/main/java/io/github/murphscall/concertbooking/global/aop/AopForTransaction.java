package io.github.murphscall.concertbooking.global.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AopForTransaction {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = joinPoint.proceed();

		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
			@Override
			public void afterCommit() {
				log.info("[TX] 트랜잭션 커밋 완료");
			}

			@Override
			public void afterCompletion(int status) {
				if (status == STATUS_COMMITTED) {
					log.info("[TX] afterCompletion: 커밋 완료 상태");
				} else if (status == STATUS_ROLLED_BACK) {
					log.info("[TX] afterCompletion: 롤백됨");
				}
			}
		});

		return result;
	}
}
