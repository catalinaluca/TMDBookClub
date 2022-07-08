package com.endava.bookrental.exceptions;

public class RenterNotFoundException extends Exception{

    public RenterNotFoundException() {
        super("This user has not rented any books!");
    }
}
