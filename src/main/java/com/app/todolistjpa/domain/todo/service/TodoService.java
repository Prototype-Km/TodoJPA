package com.app.todolistjpa.domain.todo.service;


import com.app.todolistjpa.domain.todo.dto.TodoRequestDTO;
import com.app.todolistjpa.domain.todo.dto.TodoResponseDTO;
import com.app.todolistjpa.domain.todo.entity.Todo;
import org.springframework.transaction.annotation.Transactional;

public interface TodoService {

    //등록
    public TodoResponseDTO register(TodoRequestDTO requestDTO);

    //조회
    public TodoResponseDTO getTodo(Long id);

    //전체 조회

    //수정
    public TodoResponseDTO update(Long id ,TodoRequestDTO requestDTO);

    //삭제
    public void remove(Long id);
}
