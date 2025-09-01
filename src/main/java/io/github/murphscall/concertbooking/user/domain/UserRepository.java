package io.github.murphscall.concertbooking.user.domain;

import java.util.Optional;

import io.github.murphscall.concertbooking.global.BaseRepository;

public interface UserRepository extends BaseRepository<User, Long> {
	Optional<User> findByEmail(String email);

}
