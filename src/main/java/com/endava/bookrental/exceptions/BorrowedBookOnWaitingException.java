package com.endava.bookrental.exceptions;

public class BorrowedBookOnWaitingException extends Exception{
    public BorrowedBookOnWaitingException(){
        super("You can not put yourself on the waiting list for a book you have already borrowed!");
    }
}
