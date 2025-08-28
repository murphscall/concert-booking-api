package io.github.murphscall.concertbooking.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

	public String encode(final String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean matches(final String rawPassword, final String encodedPassword) {
		return BCrypt.checkpw(rawPassword, encodedPassword);
	}

}
