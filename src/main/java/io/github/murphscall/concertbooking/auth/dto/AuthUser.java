package io.github.murphscall.concertbooking.auth.dto;

import lombok.Getter;

@Getter
public class AuthUser {
	private Long id;

	private AuthUser() {
	}

	public AuthUser(Long id) {
		this.id = id;
	}

}
