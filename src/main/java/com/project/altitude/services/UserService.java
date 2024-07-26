package com.project.altitude.services;

import com.project.altitude.domain.user.User;
import com.project.altitude.domain.user.UserCreateResponse;
import com.project.altitude.domain.user.UserData;
import com.project.altitude.domain.user.UserRequestPayload;
import com.project.altitude.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User verifyIfUserExists(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
        // TODO: CRIAR EXCEPTION DE USUÁRIO NÃO ENCONTRADO
    }

    public UserData getUser(UUID id) {
        User user = verifyIfUserExists(id);
        return new UserData(user.getUsername(), user.getFirstName(), user.getLastName());
    }

    public List<UserData> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserData> userDataList = new ArrayList<>();
        // TODO: EDITAR ESSA FORMA DE LISTA VAZIA CASO NÃO TENHA NADA PARA UMA EXCEÇÃO
        users.stream().forEach(user -> userDataList.add(new UserData(user.getUsername(), user.getFirstName(), user.getLastName())));
        return userDataList;

    }

}
