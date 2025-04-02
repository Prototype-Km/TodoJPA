package com.app.todolistjpa.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponseDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
