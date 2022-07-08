package com.endava.bookrental.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException() {
        super("This user does not exist!");
    }
}
