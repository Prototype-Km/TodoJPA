package com.app.todolistjpa.domain.user.entity;

import com.app.todolistjpa.domain.common.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Entity
@Table(name="user")
@AllArgsConstructor
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    protected User() {}

    public void update(String name,String email){
        this.name= name;
        this.email = email;
    }

    public void updatePassword(String encodedPassword){
        this.password = encodedPassword;
    }

}
