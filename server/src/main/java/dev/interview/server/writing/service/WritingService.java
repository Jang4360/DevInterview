package dev.interview.server.writing.service;

import dev.interview.server.user.domain.User;
import dev.interview.server.user.repository.UserRepository;
import dev.interview.server.writing.domain.Writing;
import dev.interview.server.writing.repository.WritingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

// 글 작성 관련 비즈니스 로직
@Service
@RequiredArgsConstructor
public class WritingService {
    private final WritingRepository writingRepository;
    private final UserRepository userRepository;

    // 사용자 글 목록 조회
    public List<Writing> getWritingsByUserId(UUID userId) {
        return writingRepository.findAllByUserId(userId);
    }

    // 글 작성 저장
    @Transactional
    public Writing createWriting(UUID userId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Writing writing = Writing.builder()
                .content(content)
                .user(user)
                .build();

        return writingRepository.save(writing);
    }

}
