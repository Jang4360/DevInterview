package dev.interview.server.user.dto;

import dev.interview.server.user.domain.User;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

}
