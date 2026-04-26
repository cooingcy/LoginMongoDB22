package io.github.mongo.login.controller.dto.response;

import io.github.mongo.login.entity.enumerable.UserRole;

import java.util.List;

public record UserResponse(
        String id,
        String username,
        String email,
        List<UserRole> roles
) {
}
