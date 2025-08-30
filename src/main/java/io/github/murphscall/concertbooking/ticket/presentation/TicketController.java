package io.github.murphscall.concertbooking.ticket.presentation;

import io.github.murphscall.concertbooking.global.dto.ApiResponse;
import io.github.murphscall.concertbooking.ticket.application.TicketService;
import io.github.murphscall.concertbooking.ticket.dto.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/concerts")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{concertId}/tickets")
    public ResponseEntity<ApiResponse<List<TicketResponse>>> getTickets(@PathVariable Long concertId) {

        List<TicketResponse> response = ticketService.getTickets(concertId);

        return ResponseEntity.ok().body(ApiResponse.success(response, null, HttpStatus.OK));

    }

}
