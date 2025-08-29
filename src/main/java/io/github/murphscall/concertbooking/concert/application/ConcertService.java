package io.github.murphscall.concertbooking.concert.application;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.murphscall.concertbooking.concert.domain.Concert;
import io.github.murphscall.concertbooking.concert.domain.ConcertRepository;
import io.github.murphscall.concertbooking.concert.domain.ConcertResponse;
import io.github.murphscall.concertbooking.concert.exception.NoSuchConcertException;
import io.github.murphscall.concertbooking.concert.mapper.ConcertModelMapper;

@Service
public class ConcertService {

	private final ConcertRepository concertRepository;
	private final ConcertModelMapper concertModelMapper;

	public ConcertService(ConcertRepository concertRepository, ConcertModelMapper concertModelMapper) {
		this.concertRepository = concertRepository;
		this.concertModelMapper = concertModelMapper;
	}

	public ConcertResponse getConcert(final Long concertId) {
		Concert concert = concertRepository.findById(concertId)
			.orElseThrow(() -> new NoSuchConcertException());

		return concertModelMapper.toDto(concert);
	}

	public Page<ConcertResponse> getConcertList(Pageable pageable) {

		Page<Concert> concertPage = concertRepository.findAll(pageable);

		return concertPage.map(concertModelMapper::toDto);
	}
}


