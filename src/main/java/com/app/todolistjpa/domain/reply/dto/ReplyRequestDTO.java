package com.app.todolistjpa.domain.reply.dto;


import com.app.todolistjpa.domain.reply.entity.Reply;
import com.app.todolistjpa.domain.todo.entity.Todo;
import com.app.todolistjpa.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReplyRequestDTO {
    @NotBlank(message = "내용을 입력해주세요.")
    private String contents;


    public Reply toEntity(User user, Todo todo){
        return Reply.builder()
                .contents(this.contents)
                .todo(todo)
                .user(user)
                .build();
    }

}
