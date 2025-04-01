package com.app.todolistjpa.domain.todo.service;


import com.app.todolistjpa.domain.todo.dto.TodoRequestDTO;
import com.app.todolistjpa.domain.todo.dto.TodoResponseDTO;
import com.app.todolistjpa.domain.todo.entity.Todo;
import com.app.todolistjpa.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class TodoService {

    //생성자 주입
    private final TodoRepository todoRepository;

    //작성
    public TodoResponseDTO register(TodoRequestDTO requestDTO){

        //requestDTO -> toEntity
        Todo entity = requestDTO.toEntity();

        //저장
        Todo saved = todoRepository.save(entity);

        //TodoResponseDTO로 응답
        return TodoResponseDTO.builder()
                .title(saved.getTitle())
                .contents(saved.getContents())
//                .userName(saved.getUser().getName())
                .build();
    }
    //조회
    public TodoResponseDTO getTodo(Long id){

        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다."));

        return TodoResponseDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .contents(todo.getContents())
//                .userName(todo.getUser().getName())
                .createdAt(todo.getCreatedAt())
                .modifiedAt(todo.getModifiedAt())
                .build();
    }

    //전체 조회
    //수정
    @Transactional
    public TodoResponseDTO update(Long id ,TodoRequestDTO requestDTO){
        //엔티티로 DB에 저장
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다."));

        todo.update(requestDTO.getTodoTitle(), requestDTO.getTodoContents());

        //responseDTO로 반환
        return TodoResponseDTO.from(todo);
    }

    //삭제
    public void remove(Long id){
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("삭제할 일정이 존재하지 않습니다."));
        todoRepository.delete(todo);
    }


}
