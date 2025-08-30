package io.github.murphscall.concertbooking.ticket.application;

import io.github.murphscall.concertbooking.concert.application.ConcertService;
import io.github.murphscall.concertbooking.concert.domain.Concert;
import io.github.murphscall.concertbooking.concert.domain.ConcertRepository;
import io.github.murphscall.concertbooking.concert.domain.ConcertResponse;
import io.github.murphscall.concertbooking.ticket.domain.TicketRepository;
import io.github.murphscall.concertbooking.ticket.dto.TicketResponse;
import io.github.murphscall.concertbooking.ticket.mapper.TicketModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ConcertRepository concertRepository;
    private final TicketModelMapper ticketModelMapper;

    public List<TicketResponse> getTickets(final Long concertId){

        concertRepository.existsById(concertId);

        List<TicketResponse> response = ticketRepository.findByConcertId(concertId)
                .stream().map(ticket -> ticketModelMapper.toDto(ticket))
                .toList();

        return response;
    }

}
