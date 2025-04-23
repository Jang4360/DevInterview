package dev.interview.server.review;

import dev.interview.server.restdocs.RestDocsSupport;
import dev.interview.server.review.controller.ReviewController;
import dev.interview.server.review.dto.ReviewRequest;
import dev.interview.server.review.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ReviewController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@Import(ReviewTestConfig.class)
@DisplayName("ReviewController 단위 테스트")
public class ReviewControllerTest extends RestDocsSupport {
    @Autowired
    private ReviewService reviewService;


    @Test
    @DisplayName("복습 완료 API 성공 테스트")
    void review_success() throws Exception {
        //given
        UUID userId = UUID.randomUUID();
        UUID qnaId = UUID.randomUUID();
        ReviewRequest request = new ReviewRequest(userId, qnaId);

        // doNothing()으로 mocking
        doNothing().when(reviewService).recordAndReschedule(userId, qnaId);

        // when @ then
        mockMvc.perform(post("/api/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("질문 복습 횟수 조회 API 성공 테스트")
    void getReviewCountByQna_success() throws Exception {
        // given
        UUID qnaId = UUID.randomUUID();
        long count = 3L;

        when(reviewService.getReviewCounterByQna(qnaId)).thenReturn(count);

        // when & then
        mockMvc.perform(get("/api/review/qna/{qnaId}/count", qnaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(count));
    }

    @Test
    @DisplayName("사용자 복습 횟수 조회 API 성공 테스트")
    void getReviewCountByUser_success() throws Exception{
        // given
        UUID userId = UUID.randomUUID();
        long count = 5L;

        when(reviewService.getReviewCounterByUser(userId)).thenReturn(count);

        // when & then
        mockMvc.perform(get("/api/review/user/{userId}/count", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(count));

    }
}
