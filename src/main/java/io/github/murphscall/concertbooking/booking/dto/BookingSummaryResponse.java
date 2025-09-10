package io.github.murphscall.concertbooking.booking.dto;

import java.time.LocalDateTime;

import io.github.murphscall.concertbooking.booking.domain.BookingStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookingSummaryResponse {
	private Long bookingId;
	private String concertName;
	private LocalDateTime concertDate;
	private LocalDateTime bookedAt;
	private BookingStatus status;

	@Override
	public String toString() {
		return "BookingSummaryResponse{" +
			"bookingId=" + bookingId +
			", concertName='" + concertName + '\'' +
			", concertDate=" + concertDate +
			", bookedAt=" + bookedAt +
			", status=" + status +
			'}';
	}
}
