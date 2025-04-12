package dev.interview.server.qna.service;

import dev.interview.server.qna.domain.Qna;
import dev.interview.server.qna.repository.QnaRepository;
import dev.interview.server.user.domain.User;
import dev.interview.server.user.repository.UserRepository;
import dev.interview.server.writing.domain.Writing;
import dev.interview.server.writing.repository.WritingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;
    private final WritingRepository writingRepository;

    // 질문 생성 저장
    @Transactional
    public Qna saveQna(UUID userId, UUID writingId, String question, String answer, LocalDateTime scheduledDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Writing writing = writingRepository.findById(writingId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        Qna qna = Qna.builder()
                .user(user)
                .writing(writing)
                .question(question)
                .answer(answer)
                .scheduledDate(scheduledDate)
                .isDeleted(false)
                .build();

        return qnaRepository.save(qna);
    }

    // 사용자별 질문 전체 조회
    public List<Qna> getAllByUserId(UUID userId) {
        return qnaRepository.findAllByUserId(userId);
    }

    // 사용자별 복습 대상 질문 조회
    public List<Qna> getTodayReview(UUID userId) {
        return qnaRepository.findAllByUserIdAndScheduledDateBeforeAndIsDeletedFalse(userId,LocalDateTime.now());
    }

}
