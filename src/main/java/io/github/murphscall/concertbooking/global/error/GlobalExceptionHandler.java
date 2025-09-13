package io.github.murphscall.concertbooking.global.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.murphscall.concertbooking.auth.exception.LoginFailedException;
import io.github.murphscall.concertbooking.global.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> handleValidationException(final MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult()
			.getFieldErrors()
			.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity
			.badRequest()
			.body(ApiResponse.error(errors, "error", HttpStatus.BAD_REQUEST));
	}

	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<ApiResponse> handleLoginFailedException(final LoginFailedException ex) {

		return ResponseEntity
			.status(HttpStatus.UNAUTHORIZED)
			.body(ApiResponse.error(null, ex.getMessage(), HttpStatus.UNAUTHORIZED));

	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ApiResponse> handleLoginFailedException(final IllegalStateException ex) {
		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(ApiResponse.error(null, ex.getMessage(), HttpStatus.BAD_REQUEST));
	}

}
