package com.app.todolistjpa.domain.user.service;

import com.app.todolistjpa.domain.common.config.PasswordEncoder;
import com.app.todolistjpa.domain.user.dto.*;
import com.app.todolistjpa.domain.user.entity.User;
import com.app.todolistjpa.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.process.internal.UserTypeResolution;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; //해시
    //가입
    public SignUpResponseDTO signUp(SignUpRequestDTO reqDTO){
        //비밀번호 해시
        String encodedPassword = passwordEncoder.encode(reqDTO.getPassword());

        //        reqDTO -> toEntity , 해시된 비밀번호 담기
        User user = reqDTO.toEntity(encodedPassword);

        User save = userRepository.save(user);

       // save-> DTO로 반환
        return SignUpResponseDTO.builder()
                .id(save.getId())
                .name(save.getName())
                .build();
    }
    //로그인
    public LoginResponseDTO login(LoginRequestDTO requestDTO, HttpSession session){

        User user = userRepository.findByEmail(requestDTO.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("다시 입력해주세요."));

        //비밀번호 비교(입력값 평문, 유저 해시 비번 비교) default메소드
        checkPassword(passwordEncoder,requestDTO.getPassword(),user.getPassword());

        // 세션에 id 저장
        session.setAttribute("loginUser",user.getId());

        return LoginResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    //회원 조회
    public UserResponseDTO getUser(Long id) {
        User foundUser = findByIdOrElseThrow(id);

      return UserResponseDTO.builder()
            .id(foundUser.getId())
            .email(foundUser.getEmail())
            .name(foundUser.getName())
            .createdAt(foundUser.getCreatedAt())
            .build();
    }

    //비밀번호 변경
    @Transactional
    public void updatePassword(Long id, UserPasswordUpdateRequestDTO requestDTO){
        //id조회
        User foundUser = findByIdOrElseThrow(id);
        //비밀번호 검증
        checkPassword(passwordEncoder,requestDTO.getCurrentPassword() ,foundUser.getPassword());
        //해시된 비밀번호
        String encodedPassword = passwordEncoder.encode(requestDTO.getNewPassword());

        foundUser.updatePassword(encodedPassword);

    }
    //회원 수정
    @Transactional
    public UserResponseDTO update(Long id, UserUpdateRequestDTO requestDTO) {
        User foundUser = findByIdOrElseThrow(id);

        foundUser.update(requestDTO.getName(), requestDTO.getEmail());
        return UserResponseDTO.builder()
                .id(foundUser.getId())
                .name(foundUser.getName())
                .email(foundUser.getEmail())
                .createdAt(foundUser.getCreatedAt())
                .modifiedAt(foundUser.getModifiedAt())
                .build();
    }

    //회원 전체 조회
    public List<UserResponseDTO> getUserList(){
        return userRepository.findAll().stream()
                .map(UserResponseDTO::toDTO).collect(Collectors.toList());
    }


    //회원 삭제
    @Transactional
    public void delete(Long id){
        User foundUser =findByIdOrElseThrow(id);
        userRepository.delete(foundUser);
    }

    @Override
    public User findByIdOrElseThrow(Long id) {
        return userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }




}
