package com.endava.bookrental.exceptions;

public class BookNotAvailableException extends Exception{

    public BookNotAvailableException(){
        super("This book is not available at the moment!");
    }
}
