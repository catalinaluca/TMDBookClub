package com.endava.bookrental.prototypes;

import com.endava.bookrental.models.Book;

import java.math.BigInteger;

public class BookPrototype {

    public static Book getBookPrototype(){
        Book bookPrototype=new Book();
        bookPrototype.setIsbn(BigInteger.valueOf(123546798));
        bookPrototype.setAuthor("author");
        bookPrototype.setTitle("title");
        bookPrototype.setBookId(1);
        return bookPrototype;
    }
}
