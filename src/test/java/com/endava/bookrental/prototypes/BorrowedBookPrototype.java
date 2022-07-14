package com.endava.bookrental.prototypes;

import com.endava.bookrental.models.BorrowedBook;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BorrowedBookPrototype {

    public static BorrowedBook getBorrowedBookPrototype(){
        BorrowedBook borrowedBookPrototype=new BorrowedBook();
        borrowedBookPrototype.setBookOwnerId(1);
        borrowedBookPrototype.setUserId(1);
        borrowedBookPrototype.setRentingId(1);
        borrowedBookPrototype.setStartDate(Timestamp.valueOf(LocalDateTime.of(2022,11,2,23,43)));
        borrowedBookPrototype.setEndDate(Timestamp.valueOf(LocalDateTime.of(2022,11,2,23,43).plusDays(7)));
        return borrowedBookPrototype;
    }
}
