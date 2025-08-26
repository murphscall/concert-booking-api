package io.github.murphscall.concertbooking.auth.presentation;

import java.util.Optional;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import io.github.murphscall.concertbooking.auth.dto.AuthUser;
import io.github.murphscall.concertbooking.auth.exception.AuthenticationException;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {


	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(AuthUser.class) &&
			parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

		Long userId = Optional
				.ofNullable((Long) request.getAttribute("userId"))
				.orElseThrow(AuthenticationException::new);


		return new AuthUser(userId);
	}
}
