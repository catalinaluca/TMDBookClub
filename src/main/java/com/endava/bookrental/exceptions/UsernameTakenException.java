package com.endava.bookrental.exceptions;

public class UsernameTakenException extends Exception{

    public UsernameTakenException() {
        super("This username is already taken!");
    }
}
