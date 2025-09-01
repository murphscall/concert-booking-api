package io.github.murphscall.concertbooking.ticket.domain;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.github.murphscall.concertbooking.global.BaseRepository;

@Repository
public interface TicketRepository extends BaseRepository<Ticket, Long> {
	List<Ticket> findByConcertId(final Long concertId);

}
