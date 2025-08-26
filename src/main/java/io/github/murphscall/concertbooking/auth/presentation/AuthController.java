package io.github.murphscall.concertbooking.auth.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.murphscall.concertbooking.auth.application.AuthService;
import io.github.murphscall.concertbooking.auth.dto.LoginRequest;
import io.github.murphscall.concertbooking.global.dto.ApiResponse;
import io.github.murphscall.concertbooking.utils.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;
	private final CookieUtil cookieUtil;

	public AuthController(AuthService authService, CookieUtil cookieUtil) {
		this.authService = authService;
		this.cookieUtil = cookieUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(
		@RequestBody final LoginRequest loginRequest,
		final HttpServletResponse response) {

		String token = authService.login(loginRequest);
		Cookie cookie = cookieUtil.createCookie(token);

		response.addCookie(cookie);

		return ResponseEntity.ok(ApiResponse.success(null, "Login Success", HttpStatus.OK));

	}
}
