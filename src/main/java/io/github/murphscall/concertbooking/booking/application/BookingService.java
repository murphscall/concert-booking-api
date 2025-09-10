package io.github.murphscall.concertbooking.booking.application;

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

	@Transactional
	public Long createBooking(final Long userId, final BookingRequest bookingRequest) {

		User user = userRepository.findByIdOrThrow(userId);
		Ticket ticket = ticketRepository.findByIdWithConcertOrThrow(bookingRequest.ticketId());

		// 예메 상태 여부 검사 및 변경
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
