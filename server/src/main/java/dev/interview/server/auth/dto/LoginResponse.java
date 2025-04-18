package dev.interview.server.auth.dto;

import java.util.UUID;

public record LoginResponse (UUID userId, String accessToken, String refreshToken){}
