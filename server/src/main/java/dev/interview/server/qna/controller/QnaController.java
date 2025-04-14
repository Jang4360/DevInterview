package dev.interview.server.qna.controller;

import dev.interview.server.qna.dto.QnaCreateRequest;
import dev.interview.server.qna.dto.QnaCreateResponse;
import dev.interview.server.qna.dto.QnaTodayResponse;
import dev.interview.server.qna.service.QnaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/qna")
@Tag(name = "QnQ", description = "GPT 질문 생성/복습 관련 API")
public class QnaController {
    private final QnaService qnaService;

    // GPT 가 생성한 질문/답변 저장하는 API
    @PostMapping("/{writingId}")
    @Operation(summary = "GPT 질문 저장", description = "GPT가 생성한 질문을 받아 DB에 저장합니다.")
    public ResponseEntity<QnaCreateResponse> createQna(
            @PathVariable UUID writingId,
            @RequestBody QnaCreateRequest request
            ) {
        var saved = qnaService.saveQna(
                request.userId(),
                writingId,
                request.question(),
                request.answer(),
                LocalDateTime.now()
        );
        return ResponseEntity.ok(new QnaCreateResponse(saved.getId()));
    }

    @GetMapping("/today")
    @Operation(summary = "오늘 복습할 질문 조회", description = "오늘 날짜 기준 복습 스케줄이 지난 질문들을 조회합니다.")
    public ResponseEntity<List<QnaTodayResponse>> getTodayReviewQnas(@RequestParam UUID userId) {
        List<QnaTodayResponse> result = qnaService.getReviewQnasForToday(userId);
        return ResponseEntity.ok(result);
    }
}
