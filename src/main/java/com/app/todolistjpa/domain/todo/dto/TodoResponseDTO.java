package com.app.todolistjpa.domain.todo.dto;

import com.app.todolistjpa.domain.todo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor //생성자 (모두초기화)
public class TodoResponseDTO {

    private final Long id;
    private final String title;
    private final String contents;
//    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public static TodoResponseDTO from(Todo todo) {
        return TodoResponseDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .contents(todo.getContents())
//            .userName(todo.getUser().getName())
                .createdAt(todo.getCreatedAt())
                .modifiedAt(todo.getModifiedAt())
                .build();
    }
}
