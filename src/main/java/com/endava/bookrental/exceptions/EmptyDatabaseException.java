package com.endava.bookrental.exceptions;

public class EmptyDatabaseException extends Exception{

    public EmptyDatabaseException() {
        super("This database is empty!");
    }
}
