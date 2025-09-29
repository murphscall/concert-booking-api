package io.github.murphscall.concertbooking.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class PathMatcher {

	private static final List<PermitRequest> PERMIT_REQUESTS = Arrays.asList(
		new PermitRequest("/api/users", HttpMethod.POST),
		new PermitRequest("/api/auth/login", HttpMethod.POST),
		new PermitRequest("/api/concerts", HttpMethod.GET),
		new PermitRequest("/actuator/prometheus", HttpMethod.GET)
	);

	public boolean isPermitted(final String path, final HttpMethod method) {
		return PERMIT_REQUESTS.stream()
			.anyMatch(permitRequest -> permitRequest.matches(path, method));
	}

	private record PermitRequest(String path, HttpMethod method) {

		public boolean matches(String path, HttpMethod method) {
			return this.path.equals(path) && this.method.equals(method);
		}
	}
}
