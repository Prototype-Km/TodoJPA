package com.app.todolistjpa.domain.todo.controller;


import com.app.todolistjpa.domain.todo.dto.TodoRequestDTO;
import com.app.todolistjpa.domain.todo.dto.TodoResponseDTO;
import com.app.todolistjpa.domain.todo.service.TodoService;
import com.app.todolistjpa.domain.user.entity.User;
import com.app.todolistjpa.domain.user.repository.UserRepository;
import com.app.todolistjpa.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/todos")
@RestController
@RequiredArgsConstructor //생성자 주입 어노테이션
public class TodoController {

    //생성자 주입
    private final TodoService todoService;
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/write")
    public ResponseEntity<TodoResponseDTO> write(HttpServletRequest request,@Valid @RequestBody TodoRequestDTO requestDTO){
        //세션 가져오기
        Long userId = (Long)request.getSession(false).getAttribute("loginUser");

        //유저 조회
        User user = userService.findByIdOrElseThrow(userId);

        //일정에 유저 추가
        TodoResponseDTO respDTO = todoService.register(user, requestDTO);
        return new ResponseEntity<>(respDTO,HttpStatus.CREATED);
    }

    //조회
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> detail(HttpServletRequest request,@PathVariable Long id){
        Long userId = (Long)request.getSession(false).getAttribute("loginUser");


        TodoResponseDTO todo = todoService.getTodo(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
    //전체조회
    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> getList(){
        List<TodoResponseDTO> todos = todoService.getList();
        return ResponseEntity.ok(todos);
    }

    //수정
    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> modify(HttpSession session, @PathVariable Long id, @RequestBody @Valid TodoRequestDTO requestDTO){
        Long userId = (Long)session.getAttribute("loginUser");
        userService.findByIdOrElseThrow(userId);
        TodoResponseDTO update = todoService.updateReply(id, userId,requestDTO);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(HttpServletRequest request,@PathVariable Long id){
        Long userId = (Long) request.getSession().getAttribute("loginUser");
        todoService.remove(id,userId);
        // 204 No Content
        return ResponseEntity.noContent().build();
    }

}
