package io.github.murphscall.concertbooking.booking.application;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.murphscall.concertbooking.booking.domain.Booking;
import io.github.murphscall.concertbooking.booking.domain.BookingRepository;
import io.github.murphscall.concertbooking.booking.dto.BookingRequest;

@SpringBootTest
public class BookingServiceConcurrencyTest {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private BookingRepository bookingRepository;

	private static final Logger log = LoggerFactory.getLogger(BookingServiceConcurrencyTest.class);

	@BeforeEach
	void setUp() {
		// 테스트 실행 전, 이전 테스트로 인해 생성되었을 수 있는 예약 정보만 삭제하여 테스트 환경을 초기화합니다.
		bookingRepository.deleteAll();
	}

	@Test
	@DisplayName("data.sql의 1번 티켓에 동시에 100개의 예약 요청이 들어올 경우, 오직 하나의 예약만 성공해야 한다.")
	void testConcurrentBooking_WithSqlData() throws InterruptedException {
		// data.sql에 의해 생성된 사용자 ID와 티켓 ID를 사용합니다.
		final Long userId = 1L;
		final Long ticketId = 1L; // 'VIP-A-1' 티켓

		int numberOfThreads = 100;
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);

		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failCount = new AtomicInteger(0);

		BookingRequest bookingRequest = new BookingRequest(ticketId);

		long startTime = System.currentTimeMillis();

		for (int i = 0; i < numberOfThreads; i++) {
			executorService.submit(() -> {
				try {
					// 각 스레드가 동일한 사용자 ID와 티켓 ID로 예약을 시도합니다.
					bookingService.createBooking(userId, bookingRequest);
					successCount.incrementAndGet();
				} catch (Exception e) {
					// 예외가 발생하면 실패 카운트 증가
					failCount.incrementAndGet();
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await(); // 모든 스레드가 작업을 마칠 때까지 대기

		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;

		executorService.shutdown();

		System.out.println("=========================================");
		System.out.println("             PERFORMANCE METRICS");
		System.out.println("-----------------------------------------");
		System.out.println("Total Requests : " + numberOfThreads);
		System.out.println("Total Time     : " + resultTime + " ms");
		if (resultTime > 0) {
			double tps = (double)numberOfThreads / (resultTime / 1000.0);
			// 초당 처리량 (Transactions Per Second)
			System.out.printf("Throughput (TPS) : %.2f%n", tps);
		} else {
			System.out.println("Throughput (TPS) : N/A (duration was 0 ms)");
		}
		System.out.println("=========================================");

		System.out.println("=========================================");
		System.out.println(" bookingService.createBooking() 성공 호출 수: " + successCount.get());
		System.out.println(" bookingService.createBooking() 실패 호출 수: " + failCount.get());
		System.out.println("=========================================");

		// 최종적으로 bookings 테이블에 저장된 예약 건수 확인
		long bookingCount = bookingRepository.count();
		assertThat(bookingCount).as("데이터베이스에는 예약이 단 한 건만 저장되어야 한다.").isEqualTo(1L);
	}

	@Test
	@DisplayName("동시에 같은 티켓 예약 시도 - 하나만 성공해야 한다")
	void testConcurrentBooking() throws InterruptedException {
		Long ticketId = 1L;
		int threadsCount = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
		CountDownLatch latch = new CountDownLatch(threadsCount);

		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failCount = new AtomicInteger(0);
		List<Long> timestamps = Collections.synchronizedList(new ArrayList<>());

		for (int i = 0; i < threadsCount; i++) {
			final long userId = i + 1;
			executorService.submit(() -> {
				try {
					long startTime = System.currentTimeMillis();
					BookingRequest request = new BookingRequest(ticketId);

					bookingService.createBooking(userId, request);

					long endTime = System.currentTimeMillis();
					timestamps.add(endTime - startTime);

					successCount.incrementAndGet();
					log.info("✅ 성공 - User: {}, 소요시간: {}ms", userId, endTime - startTime);

				} catch (Exception e) {
					failCount.incrementAndGet();
					log.info("❌ 실패 - User: {}, 이유: {}", userId, e.getMessage());
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();
		executorService.shutdown();

		// Then
		log.info("=== 테스트 결과 ===");
		log.info("성공: {}건", successCount.get());
		log.info("실패: {}건", failCount.get());
		log.info("평균 소요시간: {}ms", timestamps.stream().mapToLong(Long::longValue).average().orElse(0));

		// 실제 DB 확인
		List<Booking> bookings = bookingRepository.findAll();
		log.info("실제 생성된 예약: {}건", bookings.size());

		assertThat(successCount.get()).isEqualTo(1);  // 딱 1개만 성공
		assertThat(failCount.get()).isEqualTo(threadsCount - 1);  // 나머지는 실패
		assertThat(bookings).hasSize(1);  // DB에도 1개만 존재

	}
}