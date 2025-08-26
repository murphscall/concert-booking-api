package io.github.murphscall.concertbooking.user.presentation;

import io.github.murphscall.concertbooking.auth.dto.AuthUser;
import io.github.murphscall.concertbooking.auth.presentation.AuthenticationPrincipal;
import io.github.murphscall.concertbooking.global.dto.ApiResponse;
import io.github.murphscall.concertbooking.user.application.UserService;
import io.github.murphscall.concertbooking.user.dto.UserRequest;
import io.github.murphscall.concertbooking.user.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> userInfo(@AuthenticationPrincipal AuthUser authUser){
        UserResponse userResponse = userService.getUserInfo();
        return null;
    }

    @PatchMapping
    public ResponseEntity<Void> userUpdate(@RequestBody @Valid UserRequest userRequest) {


        return null;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> userRegister(@Valid @RequestBody final UserRequest request) {

        UserResponse userResponse = userService.register(request);

        String path = "/api/users/" + userResponse.getId();

        return ResponseEntity
                .created(URI.create(path))
                .body(ApiResponse.success(userResponse, "success" , HttpStatus.CREATED));
    }




}
