package io.github.mongo.login.repository.adapter;

import io.github.mongo.login.entity.User;
import io.github.mongo.login.repository.orm.UserOrmMongo;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRepositoryAdapter {
    private UserRepositoryAdapter() {
    }

    public static User cast(UserOrmMongo orm) {
        return new User(
                orm.id(),
                orm.username(),
                orm.password(),
                orm.email(),
                orm.roles());
    }

    public static UserOrmMongo cast(User user, PasswordEncoder encoder) {
        return new UserOrmMongo(
                user.id(),
                user.username(),
                encoder.encode(user.password()),
                user.email(),
                user.roles());
    }
}
