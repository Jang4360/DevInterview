package dev.interview.server.writing.controller;

import dev.interview.server.writing.domain.Writing;
import dev.interview.server.writing.dto.WritingCreateRequest;
import dev.interview.server.writing.dto.WritingCreateResponse;
import dev.interview.server.writing.service.WritingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/writings")
@Tag(name = "Writing", description = "글 작성 관련 API")
public class WritingController {
    private final WritingService writingService;

    // 글 생성 API
    @PostMapping
    @Operation(summary = "글 생성", description = "사용자의 글을 저장합니다.")
    public ResponseEntity<WritingCreateResponse> createWriting(@RequestBody WritingCreateRequest request) {
        Writing saved = writingService.createWriting(request.userId(), request.content());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(WritingCreateResponse.from(saved));
    }
}
