package io.github.murphscall.concertbooking.auth.exception;

public class LoginFailedException extends RuntimeException {
	public LoginFailedException(String message) {
		super(message);
	}
}
