package io.github.murphscall.concertbooking.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString(exclude = {"password"})
public class UserRequest {

	@NotBlank
	@Pattern(regexp = "^[a-z0-9._-]+@[a-z]+[.]+[a-z]{2,3}$", message = "이메일을 주소를 확인해주세요.")
	private String email;

	@NotBlank(message = "비밀번호는 필수입니다.")
	@Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}", message = "비밀번호는 영문, 숫자, 특수문자를 포함해 8자 이상이어야 합니다.")
	private String password;

	@NotBlank(message = "닉네임은 필수입니다.")
	@Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하로 입력 해주세요.")
	private String nickname;

}
