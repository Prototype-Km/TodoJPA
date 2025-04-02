package com.app.todolistjpa.domain.user.controller;


import com.app.todolistjpa.domain.user.dto.SignUpRequestDTO;
import com.app.todolistjpa.domain.user.dto.SignUpResponseDTO;
import com.app.todolistjpa.domain.user.dto.UserResponseDTO;
import com.app.todolistjpa.domain.user.dto.UserUpdateRequestDTO;
import com.app.todolistjpa.domain.user.service.UserService;
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

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO requestDTO){

        SignUpResponseDTO signUpResponseDTO = userService.signUp(requestDTO);

        return new ResponseEntity<>(signUpResponseDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getMember(@PathVariable Long id){
        UserResponseDTO foundUser = userService.getMember(id);
        return ResponseEntity.ok(foundUser);
    }

    // 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateRequestDTO reqDTO){
        UserResponseDTO updated = userService.update(id, reqDTO);
        return ResponseEntity.ok(updated);
    }
    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}