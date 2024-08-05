package com.project.altitude.domain.user;

public record UserRequestPayload (String username, String password, String firstName, String lastName, String email, String phone, String cpf){
}
