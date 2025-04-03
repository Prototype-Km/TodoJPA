package com.app.todolistjpa.domain.reply.service;

import com.app.todolistjpa.domain.reply.dto.ReplyRequestDTO;
import com.app.todolistjpa.domain.reply.dto.ReplyResponseDTO;
import com.app.todolistjpa.domain.reply.entity.Reply;
import com.app.todolistjpa.domain.reply.repository.ReplyRepository;
import com.app.todolistjpa.domain.todo.dto.TodoResponseDTO;
import com.app.todolistjpa.domain.todo.entity.Todo;
import com.app.todolistjpa.domain.todo.repository.TodoRepository;
import com.app.todolistjpa.domain.user.entity.User;
import com.app.todolistjpa.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;


    //댓글 작성
    @Transactional
    public ReplyResponseDTO register(ReplyRequestDTO requestDTO, Long userId,Long todoId) {
        //유저 조회
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Todo foundTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("할일 없음"));

        //entity로 변환
        Reply reply = requestDTO.toEntity(foundUser,foundTodo);

        //DB저장
        Reply saveReply = replyRepository.save(reply);

        return ReplyResponseDTO.builder()
                .id(saveReply.getId())
                .userName(saveReply.getUser().getName())
                .contents(saveReply.getContents())
                .createdAt(saveReply.getCreatedAt())
                .modifiedAt(saveReply.getModifiedAt())
                .build();
    }

    //댓글 수정
    @Transactional
    public ReplyResponseDTO updateReply(Long replyId, ReplyRequestDTO requestDTO, Long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        Reply foundReply = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

        if(!foundReply.getUser().getId().equals(userId)){
            throw new IllegalArgumentException("본인이 작성한것만 수정할 수 있습니다.");
        }

        //수정
        foundReply.updateContents(requestDTO.getContents());

        return ReplyResponseDTO.toDTO(foundReply);
    }

    //전체조회
    @Transactional
    public List<ReplyResponseDTO> getReplyList(){
        return replyRepository.findAll().stream().map(ReplyResponseDTO::toDTO).collect(Collectors.toList());
    }
    //삭제
    @Transactional
    public void removeReply(Long replyId, Long userId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        // 작성자 검증
        if (!reply.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("본인이 작성한 댓글만 삭제할 수 있습니다.");
        }

        replyRepository.delete(reply);
    }


}
