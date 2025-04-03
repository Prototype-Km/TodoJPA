package com.app.todolistjpa.domain.reply.controller;


import com.app.todolistjpa.domain.reply.dto.ReplyRequestDTO;
import com.app.todolistjpa.domain.reply.dto.ReplyResponseDTO;
import com.app.todolistjpa.domain.reply.service.ReplyService;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/api")
public class ReplyController {

    private final ReplyService replyService;

   //댓글 작성
   @PostMapping("/todos/{todoId}/replies")
    public ResponseEntity<ReplyResponseDTO> writeReply(HttpServletRequest request, @PathVariable Long todoId , @RequestBody @Valid ReplyRequestDTO requestDTO) {

       Long userId= (Long) request.getSession().getAttribute("loginUser");

       ReplyResponseDTO savedReply = replyService.register(requestDTO, userId,todoId);

       return ResponseEntity.status(HttpStatus.CREATED).body(savedReply);
    }

    //댓글 수정
    @PatchMapping("/replies/{replyId}")
    public ResponseEntity<ReplyResponseDTO> updateReply(
        HttpServletRequest request, @PathVariable Long replyId, @RequestBody ReplyRequestDTO requestDTO) {
        Long userId = (Long)request.getSession(false).getAttribute("loginUser");

        ReplyResponseDTO updated = replyService.updateReply(replyId,requestDTO,userId);

        return ResponseEntity.ok(updated);
    }
    //댓글 전체 조회
    @GetMapping("/replies")
    public ResponseEntity<List<ReplyResponseDTO>> getReplyList(){
        List<ReplyResponseDTO> replyList = replyService.getReplyList();
        return ResponseEntity.ok(replyList);
    }

    //댓글 삭제
    @DeleteMapping("/replies/{replyId}")
    public ResponseEntity<Void> removeReply(HttpServletRequest request,@PathVariable Long replyId){
       Long userId = (Long)request.getSession(false).getAttribute("loginUser");

       replyService.removeReply(replyId, userId);

       return ResponseEntity.noContent().build();
    }
}
