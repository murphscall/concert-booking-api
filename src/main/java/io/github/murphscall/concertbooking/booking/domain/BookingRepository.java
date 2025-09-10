package io.github.murphscall.concertbooking.booking.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.murphscall.concertbooking.global.BaseRepository;

@Repository
public interface BookingRepository extends BaseRepository<Booking, Long> {

	@Query("""
		    select b from Booking b
		    join fetch b.ticket t
		    join fetch t.concert
		    where b.user.id = :userId
		""")
	Page<Booking> findByUserId(Long userId, Pageable pageable);
}
