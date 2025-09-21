package io.github.murphscall.concertbooking.ticket.presentation;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import io.github.murphscall.concertbooking.ticket.application.TicketService;
import io.github.murphscall.concertbooking.ticket.domain.TicketGrade;
import io.github.murphscall.concertbooking.ticket.domain.TicketStatus;
import io.github.murphscall.concertbooking.ticket.dto.TicketResponse;

@WebMvcTest(TicketController.class)
@AutoConfigureRestDocs
class TicketControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private TicketService ticketService;

	@Test
	void 콘서트_티켓_목록을_반환한다() throws Exception {
		Long concertId = 1L;

		List<TicketResponse> tickets = List.of(
			new TicketResponse(1L, "A-10", TicketStatus.AVAILABLE, TicketGrade.R, new BigDecimal("120000.00")),
			new TicketResponse(2L, "A-15", TicketStatus.AVAILABLE, TicketGrade.R, new BigDecimal("120000.00"))
		);

		given(ticketService.getTickets(concertId)).willReturn(tickets);

		mockMvc.perform(get("/api/concerts/{concertId}/tickets", concertId))
			.andDo(print())
			.andDo(document("/concert/getTickets"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.size()").value(tickets.size()));

	}
}