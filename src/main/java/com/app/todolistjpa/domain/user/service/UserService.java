package com.app.todolistjpa.domain.user.service;

import com.app.todolistjpa.domain.user.dto.SignUpRequestDTO;
import com.app.todolistjpa.domain.user.dto.SignUpResponseDTO;
import com.app.todolistjpa.domain.user.entity.User;
import com.app.todolistjpa.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public SignUpResponseDTO signUp(SignUpRequestDTO reqDTO){
//        reqDTO -> toEntity
        User user = reqDTO.toEntity();

        User save = userRepository.save(user);

          // save-> DTO로 반환
        return SignUpResponseDTO.builder()
                .id(save.getId())
                .name(save.getName())
                .build();
    }
}
