package io.github.murphscall.concertbooking.support;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.murphscall.concertbooking.auth.dto.AuthUser;
import io.github.murphscall.concertbooking.booking.presentation.BookingController;
import io.github.murphscall.concertbooking.global.config.WebConfig;

@Import(WebConfig.class)
@WebMvcTest(BookingController.class)
@ExtendWith(RestDocumentationExtension.class)
public abstract class BaseRestDocsTest {

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	protected AuthUser authUser;

	@BeforeEach
	void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentationContext) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
			.apply(documentationConfiguration(restDocumentationContext).operationPreprocessors()
				.withRequestDefaults(prettyPrint())
				.withResponseDefaults(prettyPrint()))
			.build();

		authUser = new AuthUser(
			1L
		);
	}
}
