package com.endava.bookrental.exceptions;

public class BookOwnerRelationNotFoundException extends Exception {

    public BookOwnerRelationNotFoundException() {
        super("This Book-Owner relation does not exist!");
    }


    @Override
    public String toString() {
        return "This Book-Owner relation does not exist!";
    }
}
