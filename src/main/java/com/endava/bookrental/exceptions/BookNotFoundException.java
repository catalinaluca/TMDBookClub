package com.endava.bookrental.exceptions;

public class BookNotFoundException extends Exception{
    public BookNotFoundException() {
        super("This book does not exist");
    }
}
