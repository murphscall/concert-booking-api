package io.github.murphscall.concertbooking.concert.mapper;

import org.springframework.stereotype.Component;

import io.github.murphscall.concertbooking.concert.domain.Concert;
import io.github.murphscall.concertbooking.concert.domain.ConcertResponse;

@Component
public class ConcertMapper implements ConcertModelMapper{
	@Override
	public ConcertResponse toDto(Concert entity) {
		return new ConcertResponse(
				entity.getId(),
				entity.getName(),
				entity.getConcertVenue(),
				entity.getConcertDate()
		);
	}
}
