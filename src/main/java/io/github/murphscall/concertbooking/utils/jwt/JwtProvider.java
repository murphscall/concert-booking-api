package io.github.murphscall.concertbooking.utils.jwt;

import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.github.murphscall.concertbooking.user.domain.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtProvider {

	private static final String ISSUER = "io.github.murphscall.concertbooking";

	@Value("${jwt.secret.key}")
	private String secretKey;

	@Value("${jwt.expiration.time}")
	private long expiration;

	private SecretKey getSigningKey() {
		byte[] keyBytes = Base64.getDecoder().decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String createToken(final Long userId, final UserRole role) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expiration);

		Claims claims = Jwts.claims()
			.setSubject(userId.toString())
			.setIssuer(ISSUER)
			.setIssuedAt(new Date())
			.setExpiration(expiryDate);

		claims.put("role", role.name());

		return Jwts.builder()
			.setClaims(claims)
			.signWith(getSigningKey(), SignatureAlgorithm.HS256)
			.compact();
	}

	public Optional<String> resolveToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return Optional.empty();
		}

		return Arrays.stream(cookies)
			.filter(cookie -> "accessToken".equals(cookie.getName()))
			.map(Cookie::getValue)
			.findFirst();
	}

	public boolean validateToken(final String token) {
		try {
			JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
			Jws<Claims> claims = jwtParser.parseClaimsJws(token);

			return !claims.getBody().getExpiration().before(new Date());

		} catch (Exception e) {
			return false;
		}
	}

	public Long getUserIdFromToken(final String token) {
		return Long.parseLong(
			Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.getSubject()
		);
	}

}
