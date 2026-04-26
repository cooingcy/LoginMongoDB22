package io.github.mongo.login.repository.orm;

import java.util.List;

import io.github.mongo.login.entity.enumerable.UserRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "login")
public record UserOrmMongo(
        @Id
        String id,
        String username,
        String password,
        String email,
        List<UserRole> roles
) {
}