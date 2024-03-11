package com.simplestore.UserService.exceptions;

public class NoValidSessionException extends RuntimeException {

    public NoValidSessionException() {

        super("There is no session to logout!!");
    }
}
