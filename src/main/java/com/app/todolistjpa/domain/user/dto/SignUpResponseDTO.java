package com.app.todolistjpa.domain.user.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponseDTO {
    private final Long id;
    private final String name;
}
