package io.github.murphscall.concertbooking.utils.jwt;

import java.io.IOException;

import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.murphscall.concertbooking.utils.PathMatcher;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;
	private final PathMatcher pathMatcher;

	public JwtAuthenticationFilter(final JwtProvider jwtProvider, final PathMatcher pathMatcher) {
		this.jwtProvider = jwtProvider;
		this.pathMatcher = pathMatcher;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		// 테스트를 위해 모든 요청에 임시로 사용자 ID 1을 부여하고 인증을 통과시킵니다.
		request.setAttribute("userId", 1L);
		filterChain.doFilter(request, response);
		return;

		// // 요청 URL 검사
		// if (isPermitRequest(request)) {
		// 	filterChain.doFilter(request, response);
		// 	return;
		// }
		//
		// Optional<String> token = jwtProvider.resolveToken(request);
		//
		// if (token.isEmpty() || !jwtProvider.validateToken(token.get())) {
		// 	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		// 	response.getWriter().write("Authentication Failed: Invalid or missing JWT");
		// 	return;
		// }
		//
		// Long userId = jwtProvider.getUserIdFromToken(token.get());
		// request.setAttribute("userId", userId);
		//
		// filterChain.doFilter(request, response);
	}

	private boolean isPermitRequest(final HttpServletRequest request) {
		return pathMatcher.isPermitted(
			request.getRequestURI(),
			HttpMethod.valueOf(request.getMethod())
		);
	}
}


