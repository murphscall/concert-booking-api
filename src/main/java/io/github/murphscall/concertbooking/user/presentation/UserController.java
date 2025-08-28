package io.github.murphscall.concertbooking.user.presentation;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.murphscall.concertbooking.auth.dto.AuthUser;
import io.github.murphscall.concertbooking.auth.presentation.AuthenticationPrincipal;
import io.github.murphscall.concertbooking.global.dto.ApiResponse;
import io.github.murphscall.concertbooking.user.application.UserService;
import io.github.murphscall.concertbooking.user.dto.UserRequest;
import io.github.murphscall.concertbooking.user.dto.UserResponse;
import io.github.murphscall.concertbooking.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/me")
	public ResponseEntity<ApiResponse<UserResponse>> userInfo(@AuthenticationPrincipal AuthUser authUser) {
		UserResponse userResponse = userService.getUserInfo(authUser.getId());

		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userResponse, null, HttpStatus.OK));
	}

	@PatchMapping
	public ResponseEntity<ApiResponse<UserResponse>> userUpdate(@AuthenticationPrincipal AuthUser authUser,
		@RequestBody @Valid UserUpdateRequest request) {

		UserResponse userResponse = userService.update(request, authUser.getId());
		return ResponseEntity.ok().body(ApiResponse.success(userResponse, null, HttpStatus.OK));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<UserResponse>> userRegister(@Valid @RequestBody final UserRequest request) {

		UserResponse userResponse = userService.register(request);

		String path = "/api/users/" + userResponse.getId();

		return ResponseEntity
			.created(URI.create(path))
			.body(ApiResponse.success(userResponse, "success", HttpStatus.CREATED));
	}

}
