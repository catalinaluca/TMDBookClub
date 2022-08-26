package com.endava.bookrental.exceptions;

public class BookAlreadyOnWishlistException extends Exception{
    public BookAlreadyOnWishlistException(){
        super("This book is already on your wishlist!");
    }
}
