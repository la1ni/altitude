package com.project.altitude.exceptions;

public class UnavailableUsernameException extends RuntimeException{

        public UnavailableUsernameException() {
            super ("Nome de usuário indisponível");
        }

        public UnavailableUsernameException(String message) {
            super(message);
        }
}

