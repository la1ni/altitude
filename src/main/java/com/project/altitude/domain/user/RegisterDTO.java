package com.project.altitude.domain.user;

public record RegisterDTO(String username, String password, String firstName, String lastName, String email, String phone, String cpf, UserRole userRole){
}
