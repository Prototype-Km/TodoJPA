package com.app.todolistjpa.domain.todo.service;


import com.app.todolistjpa.domain.todo.dto.TodoRequestDTO;
import com.app.todolistjpa.domain.todo.dto.TodoResponseDTO;
import com.app.todolistjpa.domain.todo.entity.Todo;
import com.app.todolistjpa.domain.user.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TodoService {

    //등록
    public TodoResponseDTO register(User user, TodoRequestDTO requestDTO);

    //조회
    public TodoResponseDTO getTodo(Long id);

    //전체 조회
    public List<TodoResponseDTO> getList();

    //수정
    public TodoResponseDTO updateReply(Long id, Long userId, TodoRequestDTO requestDTO);

    //삭제
    public void remove(Long id,Long userId);
}
