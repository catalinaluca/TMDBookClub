package com.endava.bookrental.exceptions;

public class BorrowedBookNotFoundException extends Exception{

    public BorrowedBookNotFoundException() {
        super("This Book has not been borrowed yet!");
    }
}
