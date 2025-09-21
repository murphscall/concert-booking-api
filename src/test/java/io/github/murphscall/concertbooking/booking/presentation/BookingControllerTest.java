package io.github.murphscall.concertbooking.booking.presentation;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import io.github.murphscall.concertbooking.booking.application.BookingService;
import io.github.murphscall.concertbooking.booking.domain.BookingStatus;
import io.github.murphscall.concertbooking.booking.dto.BookingRequest;
import io.github.murphscall.concertbooking.booking.dto.BookingResponse;
import io.github.murphscall.concertbooking.booking.dto.BookingSummaryResponse;
import io.github.murphscall.concertbooking.global.config.WebConfig;
import io.github.murphscall.concertbooking.support.BaseRestDocsTest;

@Import(WebConfig.class)
@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(BookingController.class)
@AutoConfigureRestDocs
class BookingControllerTest extends BaseRestDocsTest {

	@MockitoBean
	private BookingService bookingService;

	private static final Long TICKET_ID = 1L;
	private static final Long BOOKING_ID = 1L;

	@Test
	void 예매_성공시_201응답과_식별자를_반환한다() throws Exception {

		BookingRequest bookingRequest = new BookingRequest(TICKET_ID);

		// given
		given(bookingService.createBooking(anyLong(), any(BookingRequest.class))).willReturn(BOOKING_ID);

		mockMvc.perform(post("/api/bookings")
				.requestAttr("userId", authUser.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(bookingRequest))

			)
			.andDo(print())
			.andDo(document("/booking/create"))
			.andExpect(status().isCreated())
			.andExpect(header().string("Location", "/api/bookings/me/" + BOOKING_ID))
			.andExpect(jsonPath("$.data.bookingId").value(BOOKING_ID));

	}

	@Test
	void 예매_조회시_상세정보를_반환한다() throws Exception {

		// 반환할 응답
		BookingResponse bookingResponse = new BookingResponse(
			BOOKING_ID,
			BookingStatus.CONFIRMED,
			"아이유 콘서트",
			"잠실 콘서트 홀",
			LocalDateTime.of(2025, 8, 9, 8, 30),
			"A-10",
			"홍길동",
			LocalDateTime.of(2025, 7, 26, 8, 5, 35)
		);

		//given
		given(bookingService.getBooking(anyLong(), anyLong())).willReturn(bookingResponse);

		mockMvc.perform(get("/api/bookings/me/{bookingId}", BOOKING_ID)
				.requestAttr("userId", authUser.getId())
			)
			.andDo(print())
			.andDo(document("/booking/getBooking"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.bookingId").value(BOOKING_ID))
			.andExpect(jsonPath("$.data.status").value(BookingStatus.CONFIRMED.toString()))
			.andExpect(jsonPath("$.data.concertName").value("아이유 콘서트"));

	}

	@Test
	void 예매_내역_조회시_최신순_예매내역을_반환한다() throws Exception {
		// 반환할 응답
		List<BookingSummaryResponse> responses = List.of(
			new BookingSummaryResponse(
				2L,
				"홍길동 콘서트",
				LocalDateTime.now(),
				LocalDateTime.of(2025, 8, 26, 8, 5, 35),
				BookingStatus.CONFIRMED),

			new BookingSummaryResponse(
				1L,
				"아이유 콘서트",
				LocalDateTime.now(),
				LocalDateTime.of(2025, 6, 9, 8, 30),
				BookingStatus.CONFIRMED)
		);

		Page<BookingSummaryResponse> page = new PageImpl<>(responses);

		//given
		given(bookingService.getBookingList(authUser.getId(), PageRequest.of(0, 10))).willReturn(page);

		mockMvc.perform(get("/api/bookings/me")
				.requestAttr("userId", authUser.getId())
			)
			.andDo(print())
			.andDo(document("/booking/getBookings"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.content[0].bookingId").value(2L))
			.andExpect(jsonPath("$.data.content[1].bookingId").value(1L))
			.andExpect(jsonPath("$.data.totalPages").value(1));
	}
}