package com.simplestore.UserService.exceptions;

public class PasswordIncorrectException extends RuntimeException{

    public PasswordIncorrectException() {

        super("Wrong Password!!");
    }
}
