package com.revature.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "User not found.")
    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFoundException(){
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Login unsuccessful.")
    @ExceptionHandler(LoginException.class)
    public void handleLoginException(){
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Register unsuccessful.")
    @ExceptionHandler(RegisterException.class)
    public void handleRegisterException(){
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User needs to be signed in to perform this operation.")
    @ExceptionHandler(AuthenticationException.class)
    public void handleAuthenticationException(){}

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User does not have the permissions to perform this operation.")
    @ExceptionHandler(AuthorizationException.class)
    public void handleAuthorizationException(){}
}
