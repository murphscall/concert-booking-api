package io.github.murphscall.concertbooking.booking.presentation;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.murphscall.concertbooking.auth.dto.AuthUser;
import io.github.murphscall.concertbooking.auth.presentation.AuthenticationPrincipal;
import io.github.murphscall.concertbooking.booking.application.BookingService;
import io.github.murphscall.concertbooking.booking.dto.BookingRequest;
import io.github.murphscall.concertbooking.booking.dto.BookingResponse;
import io.github.murphscall.concertbooking.booking.dto.BookingSummaryResponse;
import io.github.murphscall.concertbooking.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

	private final BookingService bookingService;

	@PostMapping
	public ResponseEntity<ApiResponse<Map<String, Long>>> createBooking(
		@AuthenticationPrincipal final AuthUser authUser,
		@RequestBody @Valid final BookingRequest bookingRequest) {

		Long bookingId = bookingService.createBooking(authUser.getId(), bookingRequest);

		Map<String, Long> map = new HashMap<>();

		map.put("bookingId", bookingId);

		String path = "/api/bookings/me/" + bookingId;

		return ResponseEntity.created(URI.create(path)).body(ApiResponse.success(map, null, HttpStatus.CREATED));

	}

	@GetMapping("/me/{bookingId}")
	public ResponseEntity<ApiResponse<BookingResponse>> getBooking(@AuthenticationPrincipal AuthUser authUser,
		@PathVariable Long bookingId) {

		BookingResponse bookingResponse = bookingService.getBooking(authUser.getId(), bookingId);

		return ResponseEntity.ok().body(ApiResponse.success(bookingResponse, null, HttpStatus.OK));

	}

	@GetMapping("/me")
	public ResponseEntity<ApiResponse<Page<BookingSummaryResponse>>> getBookings(
		@AuthenticationPrincipal AuthUser authUser,
		Pageable pageable) {
		Page<BookingSummaryResponse> response = bookingService.getBookingList(authUser.getId(), pageable);

		return ResponseEntity.ok().body(ApiResponse.success(response, null, HttpStatus.OK));
	}
}
