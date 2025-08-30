package io.github.murphscall.concertbooking.ticket.mapper;

import io.github.murphscall.concertbooking.global.mapper.ToDtoMapper;
import io.github.murphscall.concertbooking.ticket.domain.Ticket;
import io.github.murphscall.concertbooking.ticket.dto.TicketResponse;

public interface TicketModelMapper extends ToDtoMapper<TicketResponse, Ticket> {
}
