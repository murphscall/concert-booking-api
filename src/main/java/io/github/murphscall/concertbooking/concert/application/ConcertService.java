package io.github.murphscall.concertbooking.concert.application;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
		Concert concert = findConcertById(concertId);

		return concertModelMapper.toDto(concert);
	}

	public Page<ConcertResponse> getConcertList(
		@PageableDefault(
			size = 10,
			sort = {"createdAt", "id"},
			direction = Sort.Direction.DESC
		) Pageable pageable) {

		Page<Concert> concertPage = concertRepository.findAll(pageable);

		return concertPage.map(concertModelMapper::toDto);
	}

	/**
	 * [내부용] 다른 서비스 가 Concert 엔티티 자체를 필요로 할 때 호출
	 * 컨트롤러에서 직접 호출 x
	 * @return Concert 엔티티
	 */
	private Concert findConcertById(final Long concertId) {
		return concertRepository.findById(concertId)
				.orElseThrow(() -> new NoSuchConcertException());
	}

	private boolean exitsById(final Long concertId) {
		return concertRepository.existsById(concertId);
	}


}


