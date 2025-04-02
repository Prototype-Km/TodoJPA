package com.app.todolistjpa.domain.user.service;

import com.app.todolistjpa.domain.user.dto.SignUpRequestDTO;
import com.app.todolistjpa.domain.user.dto.SignUpResponseDTO;
import com.app.todolistjpa.domain.user.dto.UserResponseDTO;
import com.app.todolistjpa.domain.user.dto.UserUpdateRequestDTO;
import com.app.todolistjpa.domain.user.entity.User;
import com.app.todolistjpa.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //가입
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
    //회원 조회
    public UserResponseDTO getMember(Long id) {

        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

      return UserResponseDTO.builder()
            .id(foundUser.getId())
            .email(foundUser.getEmail())
            .name(foundUser.getName())
            .createdAt(foundUser.getCreatedAt())
            .build();
    }


    //회원 수정
    @Transactional
    public UserResponseDTO update(Long id, UserUpdateRequestDTO requestDTO) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        foundUser.update(requestDTO.getName(), requestDTO.getPassword());
        return UserResponseDTO.builder()
                .id(foundUser.getId())
                .name(foundUser.getName())
                .email(foundUser.getEmail())
                .createdAt(foundUser.getCreatedAt())
                .modifiedAt(foundUser.getModifiedAt())
                .build();
    }

    //회원 삭제
    @Transactional
    public void delete(Long id){
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        userRepository.delete(foundUser);
    }
}
