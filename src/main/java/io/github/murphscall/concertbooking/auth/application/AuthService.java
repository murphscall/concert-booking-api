package io.github.murphscall.concertbooking.auth.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.murphscall.concertbooking.auth.dto.LoginRequest;
import io.github.murphscall.concertbooking.user.domain.User;
import io.github.murphscall.concertbooking.user.domain.UserRepository;
import io.github.murphscall.concertbooking.utils.CookieUtil;
import io.github.murphscall.concertbooking.utils.PasswordEncoder;
import io.github.murphscall.concertbooking.utils.jwt.JwtProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;
	private final PasswordEncoder passwordEncoder;

	public AuthService(final UserRepository userRepository,
		final JwtProvider jwtProvider,
		final PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
		this.passwordEncoder = passwordEncoder;
	}

	public String login(final LoginRequest dto) {
		User user = userRepository.findByEmail(dto.getEmail())
			.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원 입니다."));

		if (!user.matchesPassword(dto.getPassword(), passwordEncoder)) {
			throw new IllegalStateException("아이디나 비밀번호가 일치하지 않습니다.");
		}

		String token = jwtProvider.createToken(user.getId(), user.getRole());

		return token;

	}

}
