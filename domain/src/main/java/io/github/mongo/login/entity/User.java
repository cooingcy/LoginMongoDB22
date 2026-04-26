package io.github.mongo.login.entity;

import io.github.mongo.login.entity.enumerable.UserRole;

import java.util.List;

public record User(
        String id,
        String username,
        String password,
        String email,
        List<UserRole> roles
) {
}
