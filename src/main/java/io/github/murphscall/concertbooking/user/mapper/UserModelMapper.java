package io.github.murphscall.concertbooking.user.mapper;

import io.github.murphscall.concertbooking.global.mapper.ModelMapper;
import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.dto.UserRequest;
import io.github.murphscall.concertbooking.user.dto.UserResponse;

public interface UserModelMapper extends ModelMapper<UserRequest, UserResponse, User> {
}
