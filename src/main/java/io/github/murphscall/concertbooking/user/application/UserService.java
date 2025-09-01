package io.github.murphscall.concertbooking.user.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.domain.UserRepository;
import io.github.murphscall.concertbooking.user.domain.UserRole;
import io.github.murphscall.concertbooking.user.dto.UserRequest;
import io.github.murphscall.concertbooking.user.dto.UserResponse;
import io.github.murphscall.concertbooking.user.dto.UserUpdateRequest;
import io.github.murphscall.concertbooking.user.mapper.UserModelMapper;
import io.github.murphscall.concertbooking.utils.PasswordEncoder;

@Service
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserModelMapper userModelMapper;
	private final UserRepository userRepository;

	public UserService(final UserRepository userRepository, final UserModelMapper userModelMapper,
		final PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userModelMapper = userModelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	public UserResponse getUserInfo(final Long userId) {

		User user = userRepository.findByIdOrThrow(userId);

		return userModelMapper.toDto(user);

	}

	@Transactional
	public UserResponse update(final UserUpdateRequest request, final Long userId) {

		User user = userRepository.findByIdOrThrow(userId);

		if (!user.matchesPassword(request.getPassword(), passwordEncoder)) {
			throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
		}

		user.updateProfile(request.getNickname());

		return userModelMapper.toDto(user);

	}

	public UserResponse register(final UserRequest userRequest) {

		String encodedPassword = passwordEncoder.encode(userRequest.getPassword());

		User user = new User(
			userRequest.getEmail(),
			encodedPassword,
			userRequest.getNickname(),
			UserRole.USER
		);

		userRepository.save(user);

		return userModelMapper.toDto(user);
	}

}
