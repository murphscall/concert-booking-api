package io.github.murphscall.concertbooking.user.application;

import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.domain.UserRepository;
import io.github.murphscall.concertbooking.user.dto.UserRequest;
import io.github.murphscall.concertbooking.user.dto.UserResponse;
import io.github.murphscall.concertbooking.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository , final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse register(final UserRequest userRequest) {

        User user = userMapper.toEntity(userRequest);

        userRepository.save(user);

        return userMapper.toDto(user);
    }
}
