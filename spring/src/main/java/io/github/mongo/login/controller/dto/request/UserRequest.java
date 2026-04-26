package io.github.mongo.login.controller.dto.request;

import io.github.mongo.login.entity.enumerable.UserRole;

import java.util.List;

public record UserRequest (
        String username,
        String email,
        String password,
        List<UserRole> roles
){
}
