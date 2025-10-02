package io.github.murphscall.concertbooking.booking.application;

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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
	private static final Logger log = LoggerFactory.getLogger(BookingService.class);

	private final BookingTransactionalService bookingTransactionalService;
	private final BookingRepository bookingRepository;
	private final BookingModelMapper bookingModelMapper;
	private final RedissonClient redissonClient;
	private final StringRedisTemplate redisTemplate;
	private static final String TICKET_CACHE_PREFIX = "ticket-status:";

	public Long createBooking(final Long userId, final BookingRequest bookingRequest) {

		final Long ticketId = bookingRequest.ticketId();
		final String lockKey = "ticket:" + ticketId;
		final RLock lock = redissonClient.getLock(lockKey);
		final String cacheKey = TICKET_CACHE_PREFIX + ticketId;

		String status = redisTemplate.opsForValue().get(cacheKey);

		if ("BOOKED".equals(status)) {
			log.warn("이미 예약된 티켓입니다.");
			throw new IllegalStateException("이미 예약된 티켓");
		}

		try {
			log.info("락 획득 시작");
			boolean isLocked = lock.tryLock(0, 3, TimeUnit.SECONDS);
			if (!isLocked) {
				log.warn("락 획득 불가");
				throw new IllegalStateException("락 획득 불가");
			}
			log.info("락 획득 성공");
			return bookingTransactionalService.createBookingTx(userId, ticketId, cacheKey, lock);

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
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
