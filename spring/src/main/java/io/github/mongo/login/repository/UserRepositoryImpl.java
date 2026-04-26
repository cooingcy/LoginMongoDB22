package io.github.mongo.login.repository;


import io.github.mongo.login.entity.User;
import io.github.mongo.login.repository.adapter.UserRepositoryAdapter;
import io.github.mongo.login.repository.mongo.UserRepositoryWithMongodb;
import io.github.mongo.login.repository.orm.UserOrmMongo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryWithMongodb repository;

    public UserRepositoryImpl(PasswordEncoder passwordEncoder, UserRepositoryWithMongodb repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Override
    public User salvar(User user) {
        UserOrmMongo orm = repository.save(UserRepositoryAdapter.cast(user, passwordEncoder));
        return UserRepositoryAdapter.cast(orm);
    }


    @Override
    public User findByUsername(String username) {
        Optional<UserOrmMongo> optional = repository.findByUsername(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }
        return UserRepositoryAdapter.cast(optional.get());
    }
}
