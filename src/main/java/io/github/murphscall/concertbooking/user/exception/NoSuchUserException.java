package io.github.murphscall.concertbooking.user.exception;

public class NoSuchUserException extends RuntimeException {
	public NoSuchUserException(String message) {
		super(message);
	}

	public NoSuchUserException() {
		this("존재하지 않는 회원입니다.");
	}
}
