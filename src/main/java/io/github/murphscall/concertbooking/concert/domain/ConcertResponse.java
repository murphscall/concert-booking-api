package io.github.murphscall.concertbooking.concert.domain;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

public record ConcertResponse(String name, String concertVenue, LocalDateTime concertDate){

}
