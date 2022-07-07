package com.endava.bookrental.modeltests;

import com.endava.bookrental.models.BorrowedBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class BorrowedBookModelTest {
    private BorrowedBook borrowedBook;

    @BeforeEach
    void initializeBorrowedBook(){
        borrowedBook=new BorrowedBook();
    }

    @Test
    void setRentingIdTest(){
        borrowedBook.setRentingId(1);
        assertThat(borrowedBook.getRentingId()).isEqualTo(1);
    }

    @Test
    void setStartDateTest(){
        borrowedBook.setStartDate(Timestamp.valueOf(LocalDateTime.of(2001,2,13,12,23)));
        assertThat(borrowedBook.getStartDate()).isEqualTo(Timestamp.valueOf(LocalDateTime.of(2001,2,13,12,23)));
    }

    @Test
    void setEndDateTest(){
        borrowedBook.setEndDate(Timestamp.valueOf(LocalDateTime.of(2001,2,13,12,23).plusDays(7)));
        assertThat(borrowedBook.getEndDate()).isEqualTo(Timestamp.valueOf(LocalDateTime.of(2001,2,13,12,23).plusDays(7)));
    }

    @Test
    void setUserIdTest(){
        borrowedBook.setUserId(1);
        assertThat(borrowedBook.getUserId()).isEqualTo(1);
    }

    @Test
    void setBookOwnerIdTest(){
        borrowedBook.setBookOwnerId(1);
        assertThat(borrowedBook.getBookOwnerId()).isEqualTo(1);
    }
}
