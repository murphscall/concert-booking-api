package io.github.murphscall.concertbooking.concert.domain;

import io.github.murphscall.concertbooking.concert.exception.NoSuchConcertException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
    default void validateById(Long concertId) {
        if (!existsById(concertId)) {
            throw new NoSuchConcertException();
        }
    }
}
