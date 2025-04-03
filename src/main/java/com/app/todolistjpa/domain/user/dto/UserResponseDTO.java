package com.app.todolistjpa.domain.user.dto;

import com.app.todolistjpa.domain.user.entity.User;
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

    public static UserResponseDTO toDTO(User user){
        return UserResponseDTO
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .build();
    }
}
