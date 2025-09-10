package io.github.murphscall.concertbooking.booking.application;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import io.github.murphscall.concertbooking.booking.domain.Booking;
import io.github.murphscall.concertbooking.booking.domain.BookingRepository;
import io.github.murphscall.concertbooking.booking.dto.BookingSummaryResponse;
import io.github.murphscall.concertbooking.booking.mapper.BookingModelMapper;
import io.github.murphscall.concertbooking.concert.domain.Concert;
import io.github.murphscall.concertbooking.concert.domain.ConcertRepository;
import io.github.murphscall.concertbooking.ticket.domain.Ticket;
import io.github.murphscall.concertbooking.ticket.domain.TicketGrade;
import io.github.murphscall.concertbooking.ticket.domain.TicketRepository;
import io.github.murphscall.concertbooking.ticket.domain.TicketStatus;
import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.domain.UserRepository;
import io.github.murphscall.concertbooking.user.domain.UserRole;

@SpringBootTest
@Transactional
class BookingServiceTest {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingModelMapper modelMapper;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ConcertRepository concertRepository;

	private User user;

	@BeforeEach
	void setUp() {

		user = new User(
			"test@gmail.com",
			"sy8583lk^^",
			"jiho",
			UserRole.USER
		);
		userRepository.save(user);

		Concert concert = new Concert(
			25,
			"아이유 콘서트",
			"잠실 고척돔",
			LocalDateTime.of(2019, 8, 9, 19, 0)
		);

		Concert concert2 = new Concert(
			25,
			"정약용 콘서트",
			"잠실 고척돔",
			LocalDateTime.of(2019, 9, 2, 14, 0)
		);

		concertRepository.saveAll(List.of(concert, concert2));

		Ticket ticket = new Ticket(
			concert,
			"A-10",
			TicketStatus.AVAILABLE,
			TicketGrade.VIP,
			new BigDecimal("220000.00")
		);

		Ticket ticket2 = new Ticket(
			concert2,
			"A-15",
			TicketStatus.AVAILABLE,
			TicketGrade.VIP,
			new BigDecimal("220000.00")
		);

		ticketRepository.saveAll(List.of(ticket, ticket2));

		Booking booking = new Booking(
			user,
			ticket
		);

		Booking booking2 = new Booking(
			user,
			ticket2
		);

		bookingRepository.save(booking);
		bookingRepository.save(booking2);

	}

	@Test
	void 사용자의_예매내역을_조회한다() {

		Long userId = 1L;

		Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());

		Page<BookingSummaryResponse> bookingResponses = bookingService.getBookingList(user.getId(), pageable);

		assertThat(bookingResponses.getTotalElements()).isEqualTo(2);
		assertThat(bookingResponses.getContent().get(0).getConcertName()).isEqualTo("정약용 콘서트");
	}
}