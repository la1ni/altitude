package com.project.altitude.controllers;

import com.project.altitude.domain.user.AuthenticationDTO;
import com.project.altitude.domain.user.RegisterDTO;
import com.project.altitude.domain.user.User;
import com.project.altitude.repositories.UserRepository;
import com.project.altitude.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

// TODO: passar as lógicas de tudo isso para o AuthenticationService
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
        var authentication = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (authenticationService.verifyIfUserExists(registerDTO.username()) != null) {
            return ResponseEntity.badRequest().build();
        } else {
            // encriptando a senha recebida
            String encodedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
            User user = new User(registerDTO, encodedPassword);
            this.userRepository.save(user);
            return ResponseEntity.ok().build();
        }

    }
}
