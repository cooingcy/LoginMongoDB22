package io.github.mongo.login.security;


import io.github.mongo.login.entity.User;
import io.github.mongo.login.repository.UserRepository;
import io.github.mongo.login.security.dto.AuthUserDetails;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsSecurity implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsSecurity(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        return new AuthUserDetails(user);
    }
}