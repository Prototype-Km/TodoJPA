package com.app.todolistjpa.domain.todo.dto;

import com.app.todolistjpa.domain.todo.entity.Todo;
import com.app.todolistjpa.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoRequestDTO {
    @NotBlank(message = "제목을 작성해주세요.")
    private final String todoTitle;
    @NotBlank(message = "내용을 작성해주세요. ")
    private final String todoContents;

    public TodoRequestDTO(String title,String contents){
        this.todoTitle =title;
        this.todoContents = contents;
    }

    //toEntity
    public Todo toEntity(){
        return Todo.builder()
            .title(this.todoTitle)
            .contents(this.todoContents)
            .build();
    }


}
