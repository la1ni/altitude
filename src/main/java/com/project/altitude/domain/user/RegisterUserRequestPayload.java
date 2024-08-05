package com.project.altitude.domain.user;

public record RegisterUserRequestPayload(String username, String password, String firstName, String lastName, String email, String phone, String cpf){
}
