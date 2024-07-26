package com.project.altitude.services;

import com.project.altitude.domain.user.User;
import com.project.altitude.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails verifyIfUserExists(String username) {
        Optional<UserDetails> user = Optional.ofNullable(userRepository.findByUsername(username));
        return user.orElse(null);
        // TODO: CRIAR EXCEPTION DE USUÁRIO NÃO ENCONTRADO
    }
}
