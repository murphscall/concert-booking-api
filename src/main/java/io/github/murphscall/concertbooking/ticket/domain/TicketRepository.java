package io.github.murphscall.concertbooking.ticket.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.murphscall.concertbooking.global.BaseRepository;
import jakarta.persistence.EntityNotFoundException;

@Repository
public interface TicketRepository extends BaseRepository<Ticket, Long> {
	List<Ticket> findByConcertId(final Long concertId);

	// ticket 과 concert 함께 조회
	@Query("SELECT t FROM Ticket t JOIN FETCH t.concert c WHERE t.id = :ticketId")
	Optional<Ticket> findByIdWithConcert(@Param("ticketId") Long ticketId);

	default Ticket findByIdWithConcertOrThrow(Long ticketId) {
		return findByIdWithConcert(ticketId)
			.orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + ticketId));
	}
}
