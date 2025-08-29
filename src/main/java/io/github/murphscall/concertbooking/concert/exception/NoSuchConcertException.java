package io.github.murphscall.concertbooking.concert.exception;

import io.github.murphscall.concertbooking.user.exception.NoSuchUserException;

public class NoSuchConcertException extends RuntimeException {
	public NoSuchConcertException(String message) {
		super(message);
	}

	public NoSuchConcertException(){
		this("존재하지 않는 콘서트 입니다.");
	}
}
