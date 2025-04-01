package com.app.todolistjpa.domain.todo.entity;

import com.app.todolistjpa.domain.common.auditing.BaseEntity;
import com.app.todolistjpa.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;




@Entity
@Table(name = "todo")
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

    //작성일 수정일
}
