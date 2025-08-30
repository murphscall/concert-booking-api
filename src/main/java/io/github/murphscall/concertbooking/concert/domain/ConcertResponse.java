package io.github.murphscall.concertbooking.concert.domain;


import java.time.LocalDateTime;

public record ConcertResponse(Long concertId, String name, String concertVenue, LocalDateTime concertDate){
}
