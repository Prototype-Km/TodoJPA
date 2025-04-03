package com.app.todolistjpa.domain.reply.dto;


import com.app.todolistjpa.domain.reply.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class ReplyResponseDTO {
    private Long id;
    private String userName;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ReplyResponseDTO toDTO(Reply reply) {
        return ReplyResponseDTO.builder()
                .id(reply.getId())
                .contents(reply.getContents())
                .userName(reply.getUser().getName())
                .createdAt(reply.getCreatedAt())
                .modifiedAt(reply.getModifiedAt())
                .build();
    }

}
