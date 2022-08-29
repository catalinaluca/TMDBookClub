package com.endava.bookrental.exceptions;

public class ExtendedOnceException extends Exception{
    public ExtendedOnceException(){
        super("The renting period for this book was already extended once!");
    }
}
