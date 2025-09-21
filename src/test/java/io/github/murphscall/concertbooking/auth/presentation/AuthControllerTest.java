package io.github.murphscall.concertbooking.auth.presentation;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.murphscall.concertbooking.auth.application.AuthService;
import io.github.murphscall.concertbooking.auth.dto.LoginRequest;
import io.github.murphscall.concertbooking.utils.CookieUtil;
import jakarta.servlet.http.Cookie;

@WebMvcTest(AuthController.class)
@AutoConfigureRestDocs
class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private CookieUtil cookieUtil;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private AuthService authService;

	@Test
	void 로그인_성공시_쿠키와_응답을_받는다() throws Exception {
		LoginRequest loginRequest = new LoginRequest(
			"test@gmail.com",
			"test1234^^"
		);

		String token = "testToken";
		Cookie mockCookie = new Cookie("accessToken", token);

		given(authService.login(any(LoginRequest.class))).willReturn(token);
		given(cookieUtil.createCookie(token)).willReturn(mockCookie);

		mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(loginRequest))
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message").value("Login Success"))
			.andExpect(cookie().value("accessToken", token));

	}
}