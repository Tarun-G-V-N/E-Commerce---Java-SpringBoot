package com.simplestore.UserService.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(long id) {

        super("User with Id: "+id+" not found!");
    }

    public UserNotFoundException(String email) {

        super("User with email: "+email+" not found!");
    }
}
