package com.app.todolistjpa.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserUpdateRequestDTO {

    @NotBlank(message = "이름을 작성해주세요.")
    private String name;

    @NotBlank(message = "이메일을 작성해주세요.")
    private String email;

}