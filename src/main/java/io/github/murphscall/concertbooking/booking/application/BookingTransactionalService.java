package io.github.murphscall.concertbooking.booking.application;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import io.github.murphscall.concertbooking.booking.domain.Booking;
import io.github.murphscall.concertbooking.booking.domain.BookingRepository;
import io.github.murphscall.concertbooking.ticket.domain.Ticket;
import io.github.murphscall.concertbooking.ticket.domain.TicketRepository;
import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.domain.UserRepository;

@Service
public class BookingTransactionalService {

	private static final Logger log = LoggerFactory.getLogger(BookingTransactionalService.class);
	private UserRepository userRepository;
	private TicketRepository ticketRepository;
	private BookingRepository bookingRepository;
	private StringRedisTemplate redisTemplate;

	public BookingTransactionalService(UserRepository userRepository, TicketRepository ticketRepository,
		BookingRepository bookingRepository, StringRedisTemplate redisTemplate) {
		this.userRepository = userRepository;
		this.ticketRepository = ticketRepository;
		this.bookingRepository = bookingRepository;
		this.redisTemplate = redisTemplate;
	}

	@Transactional
	protected Long createBookingTx(final Long userId, final Long ticketId, final String cacheKey, final RLock lock) {

		log.info("락 획득후 예매시작");

		User user = userRepository.findByIdOrThrow(userId);
		Ticket ticket = ticketRepository.findByIdWithConcertOrThrow(ticketId);

		// DB 유니크/상태 체크
		ticket.checkOrUpdate();

		Booking booking = new Booking(user, ticket);
		Booking saveBooking = bookingRepository.save(booking);

		log.info("락 획득후 예매 완료");

		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
			@Override
			public void afterCommit() {
				try {
					redisTemplate.opsForValue().set(cacheKey, "BOOKED", 3, TimeUnit.HOURS);
				} finally {
					if (lock.isHeldByCurrentThread()) {
						lock.unlock();
					}
				}
			}

			@Override
			public void afterCompletion(int status) {
				if (status == TransactionSynchronization.STATUS_ROLLED_BACK) {
					if (lock.isHeldByCurrentThread()) {
						lock.unlock();
					}
				}

			}
		});

		return saveBooking.getId();
	}
}
