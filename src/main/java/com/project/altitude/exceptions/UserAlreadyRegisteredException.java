package com.project.altitude.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(){
        super("Usuário já registrado");
    }

    UserAlreadyRegisteredException(String message){
        super(message);
    }
}
