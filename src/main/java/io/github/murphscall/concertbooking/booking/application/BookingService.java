package io.github.murphscall.concertbooking.booking.application;

import java.util.concurrent.TimeUnit;

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
import io.github.murphscall.concertbooking.global.annotation.DistributedLock;
import io.github.murphscall.concertbooking.ticket.domain.Ticket;
import io.github.murphscall.concertbooking.ticket.domain.TicketRepository;
import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.domain.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

	private final BookingRepository bookingRepository;
	private final BookingModelMapper bookingModelMapper;
	private final UserRepository userRepository;
	private final TicketRepository ticketRepository;

	@DistributedLock(
		key = "'ticket:' + #bookingRequest.ticketId()",
		waitTime = 0,
		leaseTime = 10,
		timeUnit = TimeUnit.SECONDS
	)
	public Long createBooking(final Long userId, final BookingRequest bookingRequest) {

		User user = userRepository.findByIdOrThrow(userId);
		Ticket ticket = ticketRepository.findByIdWithConcertOrThrow(bookingRequest.ticketId());

		// DB 유니크/상태 체크
		ticket.checkOrUpdate();

		Booking booking = new Booking(user, ticket);
		Booking saveBooking = bookingRepository.save(booking);

		return saveBooking.getId();
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
