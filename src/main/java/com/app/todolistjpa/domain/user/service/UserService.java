package com.app.todolistjpa.domain.user.service;

import com.app.todolistjpa.domain.common.config.PasswordEncoder;
import com.app.todolistjpa.domain.user.dto.*;
import com.app.todolistjpa.domain.user.entity.User;
import com.app.todolistjpa.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

        //가입
        public SignUpResponseDTO signUp(SignUpRequestDTO reqDTO);
//
        //로그인
        public LoginResponseDTO login(LoginRequestDTO requestDTO, HttpSession session);

        //회원 전체 조회
        public List<UserResponseDTO> getUserList();

        //회원 조회
        public UserResponseDTO getUser(Long id);

        //비밀번호 변경
        public void updatePassword(Long id, UserPasswordUpdateRequestDTO requestDTO);

        //회원 수정
        public UserResponseDTO update(Long id, UserUpdateRequestDTO requestDTO) ;

        //회원 삭제
        public void delete(Long id);

        User findByIdOrElseThrow(Long id);


        //비밀번호 검증
        default void checkPassword(PasswordEncoder passwordEncoder, String password, String encodedPassword){
            if(!passwordEncoder.matches(password,encodedPassword)){
                throw new IllegalArgumentException("다시 입력해주세요.");
            }
        }



}
