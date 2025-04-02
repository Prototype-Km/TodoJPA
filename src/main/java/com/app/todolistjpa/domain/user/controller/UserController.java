package com.app.todolistjpa.domain.user.controller;


import com.app.todolistjpa.domain.user.dto.*;
import com.app.todolistjpa.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO requestDTO){

        SignUpResponseDTO signUpResponseDTO = userService.signUp(requestDTO);

        return new ResponseEntity<>(signUpResponseDTO,HttpStatus.CREATED);
    }
    //로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO requestDTO){
        LoginResponseDTO login = userService.login(requestDTO, session);
        return ResponseEntity.ok(login);
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(){
        session.invalidate(); //세션 종료
        return ResponseEntity.noContent().build();
    }

    //회원 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getMember(@PathVariable Long id){
        UserResponseDTO foundUser = userService.getMember(id);
        return ResponseEntity.ok(foundUser);
    }

    //회원 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateRequestDTO reqDTO){
        UserResponseDTO updated = userService.update(id, reqDTO);
        return ResponseEntity.ok(updated);
    }

    //회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}