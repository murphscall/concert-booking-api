package io.github.murphscall.concertbooking.booking.application;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	private final UserRepository userRepository;
	private final TicketRepository ticketRepository;
	private final BookingRepository bookingRepository;
	private final BookingModelMapper bookingModelMapper;
	private final RedissonClient redissonClient;

	@Transactional
	public Long createBooking(final Long userId, final BookingRequest bookingRequest) {

		final Long ticketId = bookingRequest.ticketId();
		final String lockKey = "ticket:" + ticketId;
		final RLock lock = redissonClient.getLock(lockKey);

		try {
			boolean isLocked = lock.tryLock(5, 3, TimeUnit.SECONDS);

			if (!isLocked) {
				throw new IllegalStateException("락 획득 불가");
			}
			User user = userRepository.findByIdOrThrow(userId);
			Ticket ticket = ticketRepository.findByIdWithConcertOrThrow(bookingRequest.ticketId());

			// 예메 상태 여부 검사 및 변경
			ticket.checkOrUpdate();

			Booking booking = new Booking(user, ticket);
			Booking saveBooking = bookingRepository.save(booking);

			return saveBooking.getId();

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("락을 획득하는 도중 인터럽트 발생", e);
		} finally {
			if (lock.isLocked() && lock.isHeldByCurrentThread()) {
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
