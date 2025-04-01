package com.app.todolistjpa.domain.user.dto;

import com.app.todolistjpa.domain.user.entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class SignUpRequestDTO {

    private final String name;
    private final String email;
    private final String password;

    public User toEntity() {
        return User.builder()
                .name(this.getName())
                .email(this.getEmail())
                .password(this.getPassword())
                .build();
    }
}
