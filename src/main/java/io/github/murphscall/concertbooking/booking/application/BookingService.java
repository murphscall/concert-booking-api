package io.github.murphscall.concertbooking.booking.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.murphscall.concertbooking.booking.domain.Booking;
import io.github.murphscall.concertbooking.booking.domain.BookingRepository;
import io.github.murphscall.concertbooking.booking.dto.BookingRequest;
import io.github.murphscall.concertbooking.booking.dto.BookingResponse;
import io.github.murphscall.concertbooking.booking.mapper.BookingMapper;
import io.github.murphscall.concertbooking.ticket.domain.Ticket;
import io.github.murphscall.concertbooking.ticket.domain.TicketRepository;
import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

	private final UserRepository userRepository;
	private final TicketRepository ticketRepository;
	private final BookingRepository bookingRepository;
	private final BookingMapper bookingMapper;

	@Transactional
	public BookingResponse createBooking(final Long userId, final BookingRequest bookingRequest) {

		User user = userRepository.findByIdOrThrow(userId);
		Ticket ticket = ticketRepository.findByIdWithConcertOrThrow(bookingRequest.ticketId());

		// 예메 상태 여부 검사 및 변경
		ticket.book();

		Booking booking = new Booking(user, ticket);
		Booking saveBooking = bookingRepository.save(booking);

		return bookingMapper.toDto(saveBooking);

	}
}
