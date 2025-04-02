package com.app.todolistjpa.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserUpdateRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

}