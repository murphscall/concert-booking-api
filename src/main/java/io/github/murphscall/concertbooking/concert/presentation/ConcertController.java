package io.github.murphscall.concertbooking.concert.presentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.murphscall.concertbooking.concert.application.ConcertService;
import io.github.murphscall.concertbooking.concert.domain.ConcertResponse;
import io.github.murphscall.concertbooking.global.dto.ApiResponse;

@RestController
@RequestMapping("/api/concerts")
public class ConcertController {

	private final ConcertService concertService;

	public ConcertController(ConcertService concertService) {
		this.concertService = concertService;
	}

	@Transactional(readOnly = true)
	@GetMapping
	public ResponseEntity<ApiResponse<Page<ConcertResponse>>> getConcertList(Pageable pageable) {
		Page<ConcertResponse> response = concertService.getConcertList(pageable);

		return ResponseEntity.ok().body(ApiResponse.success(response, null, HttpStatus.OK));
	}

	@Transactional(readOnly = true)
	@GetMapping("/{concertId}")
	public ResponseEntity<ApiResponse<ConcertResponse>> getConcert(@PathVariable Long concertId) {
		ConcertResponse concertResponse = concertService.getConcert(concertId);

		return ResponseEntity.ok().body(ApiResponse.success(concertResponse, null, HttpStatus.OK));
	}

}
