package com.endava.bookrental.exceptions;

public class BorrowOwnBookException extends Exception {
    public BorrowOwnBookException() {
        super("You can not borrow your own book!");
    }
}
