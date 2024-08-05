package com.project.altitude.infra.security;

import com.project.altitude.exceptions.UnavailableUsernameException;
import com.project.altitude.exceptions.UserAlreadyRegisteredException;
import com.project.altitude.exceptions.UserNotFoundException;
import jakarta.servlet.UnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnavailableUsernameException.class)
    private ResponseEntity<RestErrorMessage> handleUnavailableException(final UnavailableException e) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    private ResponseEntity <RestErrorMessage> handleUserAlreadyRegisteredException(final UserAlreadyRegisteredException e) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundExceptionHandler (UserNotFoundException exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
