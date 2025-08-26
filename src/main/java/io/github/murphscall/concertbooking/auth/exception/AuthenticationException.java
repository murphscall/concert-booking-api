package io.github.murphscall.concertbooking.auth.exception;

public class AuthenticationException extends RuntimeException {
	public AuthenticationException(String message) {
		super(message);
	}
	public AuthenticationException(){
		this("인증되지 않은 사용자입니다.");
	}
}
