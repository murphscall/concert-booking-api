package io.github.murphscall.concertbooking.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;

@Component
public class CookieUtil {

	@Value("${jwt.expiration.time}")
	private long expiration;

	public Cookie createCookie(String token) {
		Cookie cookie = new Cookie("accessToken", token);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge((int)(expiration / 1000));
		// cookie.setSecure(true); 배포 환경 반드시 활성화

		return cookie;
	}

}
