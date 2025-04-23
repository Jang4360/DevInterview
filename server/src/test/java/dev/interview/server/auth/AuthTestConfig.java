package dev.interview.server.auth;

import dev.interview.server.auth.service.AuthService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class AuthTestConfig {
    @Bean
    public AuthService authService() {
        return mock(AuthService.class);
    }
}
