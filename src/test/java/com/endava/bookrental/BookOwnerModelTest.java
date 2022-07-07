package com.endava.bookrental;

import com.endava.bookrental.models.BookOwner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookOwnerModelTest {
    private BookOwner bookOwner;

    @BeforeEach
    void initializeBookOwner(){
        bookOwner=new BookOwner();
    }

    @Test
    void setBookOwnerIdTest(){
        bookOwner.setBookOwnerId(1);
        assertThat(bookOwner.getBookOwnerId()).isEqualTo(1);
    }

    @Test
    void setBookIdTest(){
        bookOwner.setBookId(1);
        assertThat(bookOwner.getBookId()).isEqualTo(1);
    }

    @Test
    void setOwnerIdTest(){
        bookOwner.setOwnerId(1);
        assertThat(bookOwner.getOwnerId()).isEqualTo(1);
    }
}

