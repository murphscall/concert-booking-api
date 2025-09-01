package io.github.murphscall.concertbooking.booking.dto;

import jakarta.validation.constraints.NotNull;

public record BookingRequest(
	@NotNull(message = "티켓 ID는 필수입니다.")
	Long ticketId
) {
}
