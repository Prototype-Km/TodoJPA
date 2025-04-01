package com.app.todolistjpa.domain.todo.controller;


import com.app.todolistjpa.domain.todo.dto.TodoRequestDTO;
import com.app.todolistjpa.domain.todo.dto.TodoResponseDTO;
import com.app.todolistjpa.domain.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/todos")
@RestController
@RequiredArgsConstructor //생성자 주입 어노테이션
public class TodoController {

    //생성자 주입
    private final TodoService todoService;

    @PostMapping("/write")
    public ResponseEntity<TodoResponseDTO> write(@Valid @RequestBody TodoRequestDTO requestDTO){
        TodoResponseDTO respDTO = todoService.register(requestDTO);
        return new ResponseEntity<>(respDTO,HttpStatus.CREATED);
    }

    //조회
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> detail(@PathVariable Long id){
        TodoResponseDTO todo = todoService.getTodo(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
    //전체조회


    //수정
    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> modify(@PathVariable Long id, @RequestBody @Valid TodoRequestDTO requestDTO){
        TodoResponseDTO update = todoService.update(id, requestDTO);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        todoService.remove(id);
        // 204 No Content
        return ResponseEntity.noContent().build();
    }
}
