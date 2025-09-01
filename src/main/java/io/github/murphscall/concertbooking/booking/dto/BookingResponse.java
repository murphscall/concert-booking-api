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
public class BookingResponse {
	private Long bookingId;
	private BookingStatus status; // 예매 상태
	private String concertName; // 콘서트 이름
	private String concertVenue; // 콘서트 장소
	private LocalDateTime concertDate; // 콘서트 날짜
	private String seatNumber;
	private String ticketHolderName; // 예매자 이름
	private LocalDateTime bookedAt; // 예매 확정 시간?

	@Override
	public String toString() {
		return "BookingResponse{" +
			"bookingId=" + bookingId +
			", status=" + status +
			", concertName='" + concertName + '\'' +
			", concertVenue='" + concertVenue + '\'' +
			", concertDate=" + concertDate +
			", seatNumber='" + seatNumber + '\'' +
			", ticketHolderName='" + ticketHolderName + '\'' +
			", bookedAt=" + bookedAt +
			'}';
	}
}
