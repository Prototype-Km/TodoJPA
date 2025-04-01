package com.app.todolistjpa.domain.todo.repository;

import com.app.todolistjpa.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
