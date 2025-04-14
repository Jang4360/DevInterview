package dev.interview.server.writing.controller;

import dev.interview.server.writing.dto.WritingCreateRequest;
import dev.interview.server.writing.dto.WritingResponse;
import dev.interview.server.writing.service.WritingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<WritingResponse> createWriting(@RequestBody WritingCreateRequest request) {
        var writing = writingService.createWriting(request.userId(), request.content());

        // 서비스 로직에서 반환한 Writing 객체를 DTO 로 변환
        WritingResponse response = new WritingResponse(
                writing.getId(),
                writing.getContent(),
                writing.getUser().getId()
        );

        return ResponseEntity.ok(response);
    }
}
