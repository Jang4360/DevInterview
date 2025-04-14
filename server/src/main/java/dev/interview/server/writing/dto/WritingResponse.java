package dev.interview.server.writing.dto;

import java.util.UUID;

// 글 응답 DTO
public record WritingResponse(
        UUID id,
        String content,
        UUID userId
) {}
