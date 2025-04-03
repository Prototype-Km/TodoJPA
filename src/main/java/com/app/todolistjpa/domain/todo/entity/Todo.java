package com.app.todolistjpa.domain.todo.entity;

import com.app.todolistjpa.domain.common.auditing.BaseEntity;
import com.app.todolistjpa.domain.reply.entity.Reply;
import com.app.todolistjpa.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@Table(name = "todo")
@AllArgsConstructor
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Mysql
    private Long id;

    private String title;

    @Column(columnDefinition = "longtext") //varchar보다 길게 작성 가능하게.
    private String contents;

    //단방향
    @ManyToOne(fetch  = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //양방향 ,todo
    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();  //null 방지

    //기본 생성자
    protected Todo(){}

    //업데이트
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
