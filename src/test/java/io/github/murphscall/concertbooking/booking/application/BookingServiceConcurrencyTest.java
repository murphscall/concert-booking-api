package io.github.murphscall.concertbooking.booking.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.murphscall.concertbooking.booking.domain.BookingRepository;
import io.github.murphscall.concertbooking.booking.dto.BookingRequest;

@SpringBootTest
public class BookingServiceConcurrencyTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

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
        executorService.shutdown();

        System.out.println("=========================================");
        System.out.println(" bookingService.createBooking() 성공 호출 수: " + successCount.get());
        System.out.println(" bookingService.createBooking() 실패 호출 수: " + failCount.get());
        System.out.println("=========================================");


        // 최종적으로 bookings 테이블에 저장된 예약 건수 확인
        long bookingCount = bookingRepository.count();
        assertThat(bookingCount).as("데이터베이스에는 예약이 단 한 건만 저장되어야 한다.").isEqualTo(1L);
    }
}