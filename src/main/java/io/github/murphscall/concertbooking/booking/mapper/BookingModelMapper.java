package io.github.murphscall.concertbooking.booking.mapper;

import io.github.murphscall.concertbooking.booking.domain.Booking;
import io.github.murphscall.concertbooking.booking.dto.BookingResponse;
import io.github.murphscall.concertbooking.booking.dto.BookingSummaryResponse;
import io.github.murphscall.concertbooking.global.mapper.ToDtoMapper;
import io.github.murphscall.concertbooking.global.mapper.ToEntityMapper;

public interface BookingModelMapper
	extends ToDtoMapper<BookingResponse, Booking>, ToEntityMapper<BookingResponse, Booking> {

	BookingSummaryResponse toSummaryDto(Booking booking);
}
