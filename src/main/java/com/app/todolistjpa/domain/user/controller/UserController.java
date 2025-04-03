package com.app.todolistjpa.domain.user.controller;


import com.app.todolistjpa.domain.user.dto.*;
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO requestDTO){

        SignUpResponseDTO signUpResponseDTO = userService.signUp(requestDTO);

        return new ResponseEntity<>(signUpResponseDTO,HttpStatus.CREATED);
    }
    //로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(HttpServletRequest request, @RequestBody @Valid LoginRequestDTO requestDTO){
        HttpSession session = request.getSession();
        LoginResponseDTO login = userService.login(requestDTO, session);
        return ResponseEntity.ok(login);
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false); //세션 없으면 Null처리 해주기.
        if (session != null) {
            //세션 종료
            session.invalidate();
            log.info("로그아웃 완");
        }
        return ResponseEntity.noContent().build();
    }

    //회원 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
        UserResponseDTO foundUser = userService.getUser(id);
        return ResponseEntity.ok(foundUser);
    }

    //회원 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(HttpServletRequest request,@PathVariable Long id, @RequestBody @Valid UserUpdateRequestDTO reqDTO){
        UserResponseDTO updated = userService.update(id, reqDTO);
        return ResponseEntity.ok(updated);
    }

    //비밀번호 변경
    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UserPasswordUpdateRequestDTO requestDTO){
        userService.updatePassword(id, requestDTO);
        return ResponseEntity.noContent().build(); // 204 응답
    }

    //회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    //회원 전체 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUserList(){
        List<UserResponseDTO> userList = userService.getUserList();
        return ResponseEntity.ok(userList);
    }
}