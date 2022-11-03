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

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Login unsuccesful.")
    @ExceptionHandler(LoginException.class)
    public void handleLoginException(){
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Register unsuccesful.")
    @ExceptionHandler(RegisterException.class)
    public void handleRegisterException(){
    }
}
