package io.github.murphscall.concertbooking.ticket.domain;

import io.github.murphscall.concertbooking.concert.exception.NoSuchConcertException;
import io.github.murphscall.concertbooking.user.exception.NoSuchUserException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByConcertId(final Long concertId);


}
