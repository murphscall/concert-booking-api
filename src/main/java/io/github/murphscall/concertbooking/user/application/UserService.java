package io.github.murphscall.concertbooking.user.application;

import io.github.murphscall.concertbooking.global.mapper.ModelMapper;
import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.domain.UserRepository;
import io.github.murphscall.concertbooking.user.dto.UserRequest;
import io.github.murphscall.concertbooking.user.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ModelMapper<UserRequest,UserResponse,User> modelMapper;
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository , final ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserResponse getUserInfo() {
        return null;
    }

    public UserResponse update(final UserRequest userRequest) {
        return null;
    }

    public UserResponse register(final UserRequest userRequest) {

        User user = modelMapper.toEntity(userRequest);

        userRepository.save(user);

        return modelMapper.toDto(user);
    }


}
