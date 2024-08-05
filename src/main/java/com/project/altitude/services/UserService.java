package com.project.altitude.services;

import com.project.altitude.domain.user.User;
import com.project.altitude.domain.user.UserCreateResponse;
import com.project.altitude.domain.user.UserData;
import com.project.altitude.domain.user.RegisterUserRequestPayload;
import com.project.altitude.exceptions.UnavailableUsernameException;
import com.project.altitude.exceptions.UserAlreadyRegisteredException;
import com.project.altitude.exceptions.UserNotFoundException;
import com.project.altitude.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User verifyIfUserExists(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        else throw new UserNotFoundException();
    }

    public UserData getUser(UUID id) {
        User user = verifyIfUserExists(id);
        return new UserData(user.getUsername(), user.getFirstName(), user.getLastName());
    }

    public List<UserData> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserData> userDataList = new ArrayList<>();
        users.forEach(user -> userDataList.add(new UserData(user.getUsername(), user.getFirstName(), user.getLastName())));
        return userDataList;
    }

    public UserCreateResponse createUser(RegisterUserRequestPayload payload) {
        User user = new User(payload);
        if (userRepository.findByUsername(payload.username()) == null) {
            if (userRepository.findByCpf(payload.cpf()) == null) {
                userRepository.save(user);
                return new UserCreateResponse(user.getId());
            }
        }
        else if (userRepository.findByCpf(payload.cpf()) != null) {
            throw new UserAlreadyRegisteredException();
        }
        // TODO: essa exception não está retornando. verificar e corrigir
        else if (userRepository.findByUsername(payload.username()) != null ) throw new UnavailableUsernameException();
        return null;
    }

    public String deleteUser(UUID id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return "Usuário deletado com sucesso";
        } else {
            throw new UserNotFoundException();
        }
    }

    public UserData updateUser(UUID id, RegisterUserRequestPayload payload) throws IllegalAccessException {
        User existentUser = verifyIfUserExists(id);
        User newUserData = new User(payload);
        if (existentUser != null) {
            for (Field field : existentUser.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object newValue = field.get(newUserData);
                Object oldValue = field.get(existentUser);

                if (oldValue != null && !oldValue.equals(newValue) && field.getType() != UUID.class) {
                    field.set(existentUser, newValue);
                    userRepository.save(existentUser);
                    return new UserData(existentUser.getUsername(), existentUser.getFirstName(), existentUser.getLastName());
                }
            }
        }
        else throw new UserNotFoundException();
        return null;
        }
    }

