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

    // 사용자 저장 (회원가입)
    public User save(User user) {
        return userRepository.save(user);
    }

}
