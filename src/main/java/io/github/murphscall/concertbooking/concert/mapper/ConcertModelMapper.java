package io.github.murphscall.concertbooking.concert.mapper;

import io.github.murphscall.concertbooking.concert.domain.Concert;
import io.github.murphscall.concertbooking.concert.domain.ConcertResponse;
import io.github.murphscall.concertbooking.global.mapper.ToDtoMapper;

public interface ConcertModelMapper extends ToDtoMapper<ConcertResponse, Concert> {
}
