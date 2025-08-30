package io.github.murphscall.concertbooking.ticket.mapper;

import io.github.murphscall.concertbooking.ticket.domain.Ticket;
import io.github.murphscall.concertbooking.ticket.dto.TicketResponse;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper implements TicketModelMapper{
    @Override
    public TicketResponse toDto(Ticket entity) {
        return new TicketResponse(
                entity.getId(),
                entity.getSeatNumber(),
                entity.getStatus(),
                entity.getGrade(),
                entity.getPrice()
        );
    }
}
