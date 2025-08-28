package io.github.murphscall.concertbooking.user.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.murphscall.concertbooking.user.exception.NoSuchUserException;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	default void validateById(Long userId) {
		if (!existsById(userId)) {
			throw new NoSuchUserException();
		}
	}
}
