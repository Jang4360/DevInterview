package dev.interview.server.global.config;

import dev.interview.server.global.exception.security.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationEntryPoint entryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
            csrf(csrf -> csrf.disable())
            // 인증 실패 시 Json 응답 보내도록 설정
            .exceptionHandling(exception -> exception
                    .authenticationEntryPoint(entryPoint)
            )
            // swagger 관련 경로 공개
            .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html"
                ).permitAll()
                .anyRequest().permitAll() // → 모든 요청 허용 (JWT 적용 전까지만)
            );
        return http.build();
    }
}
