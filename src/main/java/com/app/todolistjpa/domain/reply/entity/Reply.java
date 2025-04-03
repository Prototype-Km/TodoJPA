package com.app.todolistjpa.domain.reply.entity;

import com.app.todolistjpa.domain.common.auditing.BaseEntity;
import com.app.todolistjpa.domain.todo.entity.Todo;
import com.app.todolistjpa.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name ="reply")
@Builder @Getter @AllArgsConstructor
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "내용을 작성해주세요.")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="todo_id", nullable = false)
    private Todo todo;

    protected Reply(){;}

    //수정
    public void updateContents(String contents){
        this.contents = contents;
    }
}
