package com.app.todolistjpa.domain.user.controller;


import com.app.todolistjpa.domain.user.dto.SignUpRequestDTO;
import com.app.todolistjpa.domain.user.dto.SignUpResponseDTO;
import com.app.todolistjpa.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
