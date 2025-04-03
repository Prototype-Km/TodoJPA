package com.app.todolistjpa.domain.reply.service;


import com.app.todolistjpa.domain.reply.dto.ReplyRequestDTO;
import com.app.todolistjpa.domain.reply.dto.ReplyResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ReplyService {

    //댓글 전체조회
    List<ReplyResponseDTO> getReplyList();

    //댓글 등록
    ReplyResponseDTO register(ReplyRequestDTO requestDTO, Long userId, Long todoId);

    //댓글 수정
    ReplyResponseDTO updateReply(Long replyId, ReplyRequestDTO requestDTO, Long userId);

    //댓글 삭제
    void removeReply(Long replyId, Long userId);

}
