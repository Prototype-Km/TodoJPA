package com.app.todolistjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //Auditing 사용
@SpringBootApplication
public class TodoListJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListJpaApplication.class, args);
    }

}
