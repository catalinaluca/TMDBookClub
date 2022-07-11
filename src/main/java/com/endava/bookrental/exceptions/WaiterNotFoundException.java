package com.endava.bookrental.exceptions;

public class WaiterNotFoundException extends Exception{

    public WaiterNotFoundException() {
        super("This waiter does not exist!");
    }
}
