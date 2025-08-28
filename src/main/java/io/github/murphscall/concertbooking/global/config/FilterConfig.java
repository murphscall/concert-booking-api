package io.github.murphscall.concertbooking.global.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.murphscall.concertbooking.utils.PathMatcher;
import io.github.murphscall.concertbooking.utils.jwt.JwtAuthenticationFilter;
import io.github.murphscall.concertbooking.utils.jwt.JwtProvider;

@Configuration
public class FilterConfig {

	private final JwtProvider jwtProvider;
	private final PathMatcher pathMatcher;

	public FilterConfig(final JwtProvider jwtProvider, final PathMatcher pathMatcher) {
		this.jwtProvider = jwtProvider;
		this.pathMatcher = pathMatcher;
	}

	@Bean
	public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilter() {
		FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new JwtAuthenticationFilter(jwtProvider, pathMatcher));
		registrationBean.addUrlPatterns("/api/*");
		registrationBean.setOrder(1);

		return registrationBean;

	}
}
