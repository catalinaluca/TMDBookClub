package com.endava.bookrental.prototypes;

import com.endava.bookrental.models.BookOwner;

public class BookOwnerPrototype {

    public static BookOwner getBookOwnerPrototype(){
        BookOwner bookOwnerPrototype=new BookOwner();
        bookOwnerPrototype.setOwnerId(1);
        bookOwnerPrototype.setBookId(1);
        bookOwnerPrototype.setBookOwnerId(1);
        return bookOwnerPrototype;
    }
}
