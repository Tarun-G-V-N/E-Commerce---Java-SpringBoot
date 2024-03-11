package com.simplestore.productservice.exceptions;

public class InvalidTitleException extends RuntimeException{

    public InvalidTitleException(String title) {

        super("Title: "+title+" is not valid.");
    }
}
