package io.github.mongo.login.controller.adapter;

import io.github.mongo.login.controller.dto.request.UserRequest;
import io.github.mongo.login.entity.User;

import java.util.UUID;

public class UserControllerAdapter {
    private UserControllerAdapter() {
    }

    public static User cast(UserRequest request) {
        return new User(
                UUID.randomUUID().toString(),
                request.username(),
                request.password(),
                request.email(),
                request.roles());
    }
}