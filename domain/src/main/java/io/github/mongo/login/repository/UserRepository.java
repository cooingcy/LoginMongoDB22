package io.github.mongo.login.repository;

import io.github.mongo.login.entity.User;

import java.util.List;

public interface UserRepository {
    User salvar (User user);
    User findByUsername(String username);
}
