package io.github.murphscall.concertbooking.booking.application;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.murphscall.concertbooking.booking.domain.Booking;
import io.github.murphscall.concertbooking.booking.domain.BookingRepository;
import io.github.murphscall.concertbooking.booking.dto.BookingRequest;
import io.github.murphscall.concertbooking.booking.dto.BookingResponse;
import io.github.murphscall.concertbooking.booking.dto.BookingSummaryResponse;
import io.github.murphscall.concertbooking.booking.mapper.BookingModelMapper;
import io.github.murphscall.concertbooking.ticket.domain.Ticket;
import io.github.murphscall.concertbooking.ticket.domain.TicketRepository;
import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.domain.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
	private static final Logger log = LoggerFactory.getLogger(BookingService.class);

	private final UserRepository userRepository;
	private final TicketRepository ticketRepository;
	private final BookingRepository bookingRepository;
	private final BookingModelMapper bookingModelMapper;
	private final RedissonClient redissonClient;
	private final StringRedisTemplate redisTemplate;
	private static final String TICKET_CACHE_PREFIX = "ticket-status:";

	@Transactional
	public Long createBooking(final Long userId, final BookingRequest bookingRequest) {
		final String requestId = UUID.randomUUID().toString().substring(0, 8);
		log.info("[{}] 예매 요청 시작", requestId);

		final Long ticketId = bookingRequest.ticketId();
		final String lockKey = "ticket:" + ticketId;
		final RLock lock = redissonClient.getLock(lockKey);
		final String cacheKey = TICKET_CACHE_PREFIX + ticketId;

		String status = redisTemplate.opsForValue().get(cacheKey);

		if ("BOOKED".equals(status)) {
			log.warn("[{}] 이미 예약된 좌석입니다.", requestId);
			throw new IllegalStateException("이미 예약된 좌석입니다.");
		}

		log.info("[{}] 락 획득 시도... (Key: {})", requestId, lockKey);
		try {
			boolean isLocked = lock.tryLock(0, 3, TimeUnit.SECONDS);

			if (!isLocked) {
				log.warn("[{}] 락 획득 실패!", requestId);
				throw new IllegalStateException("락 획득 불가");
			}

			log.info("[{}] 락 획득 성공. 예매 로직 시작.", requestId);
			User user = userRepository.findByIdOrThrow(userId);
			Ticket ticket = ticketRepository.findByIdWithConcertOrThrow(bookingRequest.ticketId());

			// 예메 상태 여부 검사 및 변경
			log.info("[{}] 좌석 상태 확인 및 변경 시도...", requestId);
			ticket.checkOrUpdate();
			log.info("[{}] 좌석 상태 변경 완료.", requestId);

			Booking booking = new Booking(user, ticket);
			Booking saveBooking = bookingRepository.save(booking);

			// 예매 취소 시 캐시 삭제 필요
			redisTemplate.opsForValue().set(cacheKey, "BOOKED", 3, TimeUnit.HOURS);

			log.info("[{}] 예매 성공! (Booking ID: {})", requestId, saveBooking.getId());
			return saveBooking.getId();

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			if (lock.isLocked() && lock.isHeldByCurrentThread()) {
				log.info("[{}] 락 해제.", requestId);
				lock.unlock();
			}
		}
	}

	@Transactional(readOnly = true)
	public Page<BookingSummaryResponse> getBookingList(Long userId, @PageableDefault(
		size = 10,
		sort = {"createdAt", "id"},
		direction = Sort.Direction.DESC
	) Pageable pageable) {

		Page<Booking> pageResponse = bookingRepository.findByUserId(userId, pageable);

		return pageResponse.map(bookingModelMapper::toSummaryDto);
	}

	@Transactional(readOnly = true)
	public BookingResponse getBooking(Long userId, Long bookingId) {

		Booking booking = bookingRepository.findByIdWithDetails(bookingId)
			.orElseThrow(() -> new EntityNotFoundException());

		booking.validateOwner(userId);

		return bookingModelMapper.toDto(booking);
	}
}
