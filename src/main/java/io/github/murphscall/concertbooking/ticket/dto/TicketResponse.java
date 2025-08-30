package io.github.murphscall.concertbooking.ticket.dto;

import io.github.murphscall.concertbooking.ticket.domain.TicketGrade;
import io.github.murphscall.concertbooking.ticket.domain.TicketStatus;

import java.math.BigDecimal;

public record TicketResponse (Long ticketId, String SeatNumber, TicketStatus status, TicketGrade grade, BigDecimal price){}
