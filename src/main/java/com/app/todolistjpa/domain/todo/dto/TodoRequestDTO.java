package com.app.todolistjpa.domain.todo.dto;

import com.app.todolistjpa.domain.todo.entity.Todo;
import com.app.todolistjpa.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

//할일 제목 10글자 유저명 4글자 이내
@Getter
public class TodoRequestDTO {
    @NotBlank(message = "제목을 작성해주세요.")
    @Size(max = 10, message = "제목은 10글자 이내로 작성해주세요." )
    private final String todoTitle;

    @NotBlank(message = "내용을 작성해주세요. ")
    private final String todoContents;

    public TodoRequestDTO(String title,String contents){
        this.todoTitle =title;
        this.todoContents = contents;
    }

    //toEntity
    public Todo toEntity(User user){
        return Todo.builder()
            .title(this.todoTitle)
            .contents(this.todoContents)
            .user(user)
            .build();
    }


}
