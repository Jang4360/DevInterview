package dev.interview.server.user.service;

import dev.interview.server.global.exception.NotFoundException;
import dev.interview.server.user.domain.User;
import dev.interview.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

// 사용자 관련 비즈니스 로직
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 이메일로 사용자 조회 (로그인, 인증시 사용)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 사용자 저장 (회원가입)
    public User save(User user) {
        return userRepository.save(user);
    }

    // 존재 여부 확인
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // 사용자 단건 조회 (로그인 세션 검증)
    public User getById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자입니다."));
    }
}
