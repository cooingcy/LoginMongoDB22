package io.github.mongo.login.controller.adapter;


import io.github.mongo.login.controller.dto.request.LoginRequest;
import io.github.mongo.login.entity.Login;

public final class LoginControllerAdapter {

    private LoginControllerAdapter() {
    }

    public static Login cast(LoginRequest request) {
        return new Login(request.username(), request.password());
    }
}

