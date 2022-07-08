package com.endava.bookrental.exceptions;

public class UsernameNullException extends Exception{
    public UsernameNullException() {
        super("The username should not be null!");
    }

}
