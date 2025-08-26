package io.github.murphscall.concertbooking.user.application;


import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.domain.UserRepository;
import io.github.murphscall.concertbooking.user.domain.UserRole;
import io.github.murphscall.concertbooking.user.dto.UserRequest;
import io.github.murphscall.concertbooking.user.dto.UserResponse;
import io.github.murphscall.concertbooking.user.mapper.UserModelMapper;
import io.github.murphscall.concertbooking.utils.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserModelMapper userModelMapper;
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository , final UserModelMapper userModelMapper , final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userModelMapper = userModelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse getUserInfo() {
        return null;
    }

    public UserResponse update(final UserRequest userRequest) {
        return null;
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
