package io.github.mongo.login.controller;

import io.github.mongo.login.controller.adapter.UserControllerAdapter;
import io.github.mongo.login.controller.dto.request.UserRequest;
import io.github.mongo.login.controller.dto.response.UserResponse;
import io.github.mongo.login.entity.User;
import io.github.mongo.login.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/salvar")
    public UserResponse salvar(@RequestBody UserRequest request) {
        User save = userRepository.salvar(UserControllerAdapter.cast(request));
        return new UserResponse(
                save.id(),
                save.username(),
                save.email(),
                save.roles());
    }
}
