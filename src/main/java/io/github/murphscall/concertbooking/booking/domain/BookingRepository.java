package io.github.murphscall.concertbooking.booking.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.murphscall.concertbooking.global.BaseRepository;

@Repository
public interface BookingRepository extends BaseRepository<Booking, Long> {

	@Query(
		"SELECT b FROM Booking b " +
			"JOIN FETCH b.user u " +
			"JOIN FETCH b.ticket t " +
			"JOIN FETCH t.concert c " +
			"WHERE b.id = :bookingId"
	)
	Optional<Booking> findByIdWithDetails(@Param("bookingId") Long bookingId);
}
