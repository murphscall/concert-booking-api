package io.github.murphscall.concertbooking.booking.presentation;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.murphscall.concertbooking.auth.dto.AuthUser;
import io.github.murphscall.concertbooking.auth.presentation.AuthenticationPrincipal;
import io.github.murphscall.concertbooking.booking.application.BookingService;
import io.github.murphscall.concertbooking.booking.dto.BookingRequest;
import io.github.murphscall.concertbooking.booking.dto.BookingResponse;
import io.github.murphscall.concertbooking.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

	private final BookingService bookingService;

	@PostMapping
	public ResponseEntity<ApiResponse<BookingResponse>> createBooking(@AuthenticationPrincipal final AuthUser authUser,
		@RequestBody @Valid final BookingRequest bookingRequest) {

		BookingResponse response = bookingService.createBooking(authUser.getId(), bookingRequest);

		String path = "/api/users/me/bookings" + response.getBookingId();

		return ResponseEntity.created(URI.create(path)).body(ApiResponse.success(response, null, HttpStatus.CREATED));

	}
}
