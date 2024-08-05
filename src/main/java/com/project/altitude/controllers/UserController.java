package com.project.altitude.controllers;

import com.project.altitude.domain.user.UserCreateResponse;
import com.project.altitude.domain.user.UserData;
import com.project.altitude.domain.user.RegisterUserRequestPayload;
import com.project.altitude.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserData> findUser(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<List<UserData>> findAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody RegisterUserRequestPayload registerUserRequestPayload){
        return ResponseEntity.ok(userService.createUser(registerUserRequestPayload));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserData> updateUser(@PathVariable UUID id, @RequestBody RegisterUserRequestPayload payload) throws IllegalAccessException {
        return ResponseEntity.ok(userService.updateUser(id, payload));
    }
}
