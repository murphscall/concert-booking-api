package io.github.murphscall.concertbooking.global.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponse<T> {

	private int statusCode;
	private String message;
	private T data;

	public ApiResponse(final int statusCode, final String message, final T data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResponse<T> success(final T data, final String message, final HttpStatus status) {
		return new ApiResponse<>(status.value(), message, data);
	}

	public static <T> ApiResponse<T> error(final T data, final String message, final HttpStatus status) {
		return new ApiResponse<>(status.value(), message, data);
	}

}
