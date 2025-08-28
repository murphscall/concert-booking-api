package io.github.murphscall.concertbooking.user.mapper;

import org.springframework.stereotype.Component;

import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.domain.UserRole;
import io.github.murphscall.concertbooking.user.dto.UserRequest;
import io.github.murphscall.concertbooking.user.dto.UserResponse;

@Component
public class UserMapper implements UserModelMapper {

	@Override
	public UserResponse toDto(final User user) {
		return new UserResponse(
			user.getId(),
			user.getEmail(),
			user.getNickname(),
			user.getCreatedAt()
		);
	}

	@Override
	public User toEntity(final UserRequest dto) {
		return new User(
			dto.getEmail(),
			dto.getPassword(),
			dto.getNickname(),
			UserRole.USER
		);
	}
}
