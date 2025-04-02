package com.app.todolistjpa.domain.user.dto;

import lombok.Getter;

@Getter
public class UserPasswordUpdateRequestDTO {
    private String currentPassword;
    private String newPassword;
}

