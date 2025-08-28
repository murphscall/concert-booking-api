package io.github.murphscall.concertbooking.user.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@ToString
public class UserResponse {

	private Long id;
	private String email;
	private String nickname;
	private LocalDateTime createdAt;

}
