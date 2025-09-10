package io.github.murphscall.concertbooking.booking.mapper;

import org.springframework.stereotype.Component;

import io.github.murphscall.concertbooking.booking.domain.Booking;
import io.github.murphscall.concertbooking.booking.dto.BookingResponse;
import io.github.murphscall.concertbooking.booking.dto.BookingSummaryResponse;

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
		);
	}

	@Override
	public BookingSummaryResponse toSummaryDto(Booking entity) {
		return new BookingSummaryResponse(
			entity.getId(),
			entity.getTicket().getConcert().getName(),
			entity.getTicket().getConcert().getConcertDate(),
			entity.getBookedAt(),
			entity.getStatus()
		);
	}

	@Override
	public Booking toEntity(BookingResponse dto) {
		return null;
	}
}
