package com.app.todolistjpa.domain.user.dto;

import com.app.todolistjpa.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class SignUpRequestDTO {

    @NotBlank(message = "이름을 작성해주세요.")
    @Size(max = 4, message = "이름은 4글자 이내여야 합니다.")
    private final String name;

    @NotBlank(message = "이메일을 작성해주세요.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "올바른 이메일 형식이어야 합니다."
    )
    @Email
    private final String email;

    @NotBlank(message = "비밀번호를 작성해주세요.")
    private final String password;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .name(this.getName())
                .email(this.getEmail())
                .password(encodedPassword)
                .build();
    }
}
