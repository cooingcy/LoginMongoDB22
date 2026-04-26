package io.github.mongo.login.controller.dto.request;

import java.util.List;

public record LoginRequest(
        String username,
        String password
) {
}
