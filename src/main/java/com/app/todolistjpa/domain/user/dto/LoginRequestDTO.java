package com.app.todolistjpa.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequestDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
