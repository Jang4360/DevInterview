package dev.interview.server.review.service;

import dev.interview.server.global.exception.NotFoundException;
import dev.interview.server.qna.domain.Qna;
import dev.interview.server.qna.repository.QnaRepository;
import dev.interview.server.review.domain.ReviewQueue;
import dev.interview.server.review.repository.ReviewRepository;
import dev.interview.server.user.domain.User;
import dev.interview.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

// 복습 이력 관련 비즈니스 로직
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final QnaRepository qnaRepository;

    // 복습 완료 기록 저장
    @Transactional
    public ReviewQueue recordReview(UUID userId, UUID qnaId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자입니다."));
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 질문입니다."));

        ReviewQueue review = ReviewQueue.builder()
                .user(user)
                .qna(qna)
                .reviewedAt(LocalDateTime.now())
                .build();

        return reviewRepository.save(review);
    }

    // 특정 qna 복습 횟수 조회
    public Long getReviewCounterByQna(UUID qnaId) {
        return reviewRepository.countByQnaId(qnaId);
    }

    // 사용자 전체 복습 횟수 조회
    public long getReviewCounterByUser(UUID userId) {
        return reviewRepository.countByUserId(userId);
    }
}
