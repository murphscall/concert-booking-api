package io.github.murphscall.concertbooking.user.presentation;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.murphscall.concertbooking.global.config.WebConfig;
import io.github.murphscall.concertbooking.user.application.UserService;
import io.github.murphscall.concertbooking.user.dto.UserRequest;
import io.github.murphscall.concertbooking.user.dto.UserResponse;
import io.github.murphscall.concertbooking.user.dto.UserUpdateRequest;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(UserController.class)
@Import(WebConfig.class)
class UserControllerTest {

	private final Long TEST_USER_ID = 1L;
	private final String TEST_USER_EMAIL = "test@example.com";
	private final String TEST_USER_NICKNAME = "jinhoo";
	private final String TEST_USER_PASSWORD = "password123^^";
	private UserRequest testUserRequest;
	private UserResponse testUserResponse;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private UserService userService;

	@BeforeEach
	void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentationContext) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
			.apply(documentationConfiguration(restDocumentationContext).operationPreprocessors()
				.withRequestDefaults(prettyPrint())
				.withResponseDefaults(prettyPrint()))
			.build();
		this.testUserRequest = new UserRequest(
			TEST_USER_EMAIL,
			TEST_USER_NICKNAME,
			TEST_USER_PASSWORD
		);
		this.testUserResponse = new UserResponse(
			TEST_USER_ID,
			TEST_USER_EMAIL,
			TEST_USER_NICKNAME,
			LocalDateTime.now()
		);
	}

	private void expectUserResponse(ResultActions resultActions, UserResponse expectedResponse) throws Exception {
		resultActions
			.andExpect(jsonPath("$.data.id").value(expectedResponse.getId()))
			.andExpect(jsonPath("$.data.email").value(expectedResponse.getEmail()))
			.andExpect(jsonPath("$.data.createdAt").exists());
	}

	@DisplayName("회원 정보를 가져온다.")
	@Test
	void userInfo() throws Exception {

		given(userService.getUserInfo(any())).willReturn(testUserResponse);

		ResultActions resultActions = mockMvc.perform(get("/api/users/me").requestAttr("userId", 1L));

		resultActions
			.andDo(print())
			.andDo(document("/users/userInfo"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.statusCode").value(200));

		expectUserResponse(resultActions, testUserResponse);
	}

	@DisplayName("닉네임 변경 성공 시 변경 된 회원 정보를 받는다.")
	@Test
	void userUpdate() throws Exception {

		String updatedNickname = "jinhoo123";
		UserResponse updatedUserResponse = new UserResponse(TEST_USER_ID, TEST_USER_EMAIL, updatedNickname,
			LocalDateTime.now());

		given(userService.update(any(UserUpdateRequest.class), anyLong())).willReturn(updatedUserResponse);

		UserUpdateRequest updateRequest = new UserUpdateRequest(updatedNickname, TEST_USER_PASSWORD);

		ResultActions resultActions = mockMvc.perform(patch("/api/users")
			.requestAttr("userId", TEST_USER_ID)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(updateRequest))
		);

		resultActions
			.andDo(print())
			.andDo(document("/users/userUpdate"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.nickname").value(updatedNickname));

		expectUserResponse(resultActions, testUserResponse);

	}

	@DisplayName("회원 정보 수정 요청 시 닉네임이 비어있다면 400 코드를 반환한다.")
	@Test
	void userUpdate_WithBlankNickName_ShouldFail() throws Exception {
		UserUpdateRequest invaildRequest = new UserUpdateRequest(" ", "test1234^^");

		ResultActions resultActions = mockMvc.perform(patch("/api/users")
			.requestAttr("userId", TEST_USER_ID)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(invaildRequest))
		);

		resultActions
			.andDo(print())
			.andDo(document("users/update-fail-blank-nickname"))
			.andExpect(status().isBadRequest());
	}

	@DisplayName("회원 정보 수정 요청 시 비밀번호가 비어있다면 400 코드를 반환한다.")
	@Test
	void userUpdate_WithBlankPassword_ShouldFail() throws Exception {
		UserUpdateRequest invaildRequest = new UserUpdateRequest("jinhooman", " ");

		mockMvc.perform(patch("/api/users").requestAttr("userId", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invaildRequest)))
			.andDo(print())
			.andDo(document("users/update-fail-blank-password"))
			.andExpect(status().isBadRequest());
	}

	@DisplayName("회원가입 요청을 받고 반환한다.")
	@Test
	void userRegister() throws Exception {
		// given
		UserRequest userRequest = new UserRequest("test@gmail.com", "sy8583lk^^", "jinhoo");

		UserResponse userResponse = new UserResponse(1L, "test@gmail.com", "jinhoo", LocalDateTime.now());

		given(userService.register(any(UserRequest.class))).willReturn(userResponse);

		mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userRequest)))
			.andDo(print())
			.andDo(document("users/userRegister", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.statusCode").value(201))
			.andExpect(jsonPath("$.data.id").value(1L))
			.andExpect(jsonPath("$.data.email").value("test@gmail.com"))
			.andExpect(jsonPath("$.data.nickname").value("jinhoo"));
	}
}