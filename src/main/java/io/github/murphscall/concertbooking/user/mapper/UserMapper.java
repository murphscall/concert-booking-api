package io.github.murphscall.concertbooking.user.mapper;

import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.dto.UserRequest;
import io.github.murphscall.concertbooking.user.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    

    public User toEntity(final UserRequest dto){
        return new User(
                dto.getEmail(),
                dto.getPassword(), // 추후에 암호화 설정
                dto.getNickname()
        );
    }

    public UserResponse toDto(final User user){
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getCreatedAt()
        );
    }


}
