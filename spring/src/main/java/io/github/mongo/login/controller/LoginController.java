package io.github.mongo.login.controller;

import io.github.mongo.login.controller.adapter.LoginControllerAdapter;
import io.github.mongo.login.controller.dto.response.LoginResponse;
import io.github.mongo.login.entity.Token;
import io.github.mongo.login.security.TokenSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.mongo.login.controller.dto.request.LoginRequest;

@RestController
public class LoginController {
    private final TokenSecurity tokenSecurity;

    public LoginController(TokenSecurity tokenSecurity) {
        this.tokenSecurity = tokenSecurity;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/v1/auth")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Token token = tokenSecurity.gerarToken(LoginControllerAdapter.cast(request));
        return new LoginResponse(token.value());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/v1/auth/forget/{username}")
    public String forgetPassword(@PathVariable("username") String username) {
        return "Olá " + username + " enviamos sua senha para o seu email";
    }
}