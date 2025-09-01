package io.github.murphscall.concertbooking.booking.mapper;

import org.springframework.stereotype.Component;

import io.github.murphscall.concertbooking.booking.domain.Booking;
import io.github.murphscall.concertbooking.booking.dto.BookingResponse;

@Component
public class BookingMapper implements BookingModelMapper {
	@Override
	public BookingResponse toDto(Booking entity) {
		return new BookingResponse(
			entity.getId(),
			entity.getStatus(),
			entity.getTicket().getConcert().getName(),
			entity.getTicket().getConcert().getConcertVenue(),
			entity.getTicket().getConcert().getConcertDate(),
			entity.getTicket().getSeatNumber(),
			entity.getUser().getNickname(),
			entity.getBookedAt()
			// Join Fetch 필요 N+1 이슈 발생 할 것
		);
	}

	@Override
	public Booking toEntity(BookingResponse dto) {
		return null;
	}
}
