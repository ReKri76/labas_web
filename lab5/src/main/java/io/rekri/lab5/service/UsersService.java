package io.rekri.lab5.service;

import io.rekri.lab5.DTO.User;
import io.rekri.lab5.config.security.Roles;
import io.rekri.lab5.entities.Users;
import io.rekri.lab5.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    final private UsersRepository repository;
    final private PasswordEncoder passwordEncoder;

    public void createUser(final User user){
        repository.save(new Users.Builder()
                .name(user.getNickname())
                .role(Roles.ROLE_USER.toString())
                .password(passwordEncoder.encode(user.getPassword()))
                .build()
        );
    }
}
