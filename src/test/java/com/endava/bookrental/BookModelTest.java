package com.endava.bookrental;

import com.endava.bookrental.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class BookModelTest {
    private static Book book;

    @BeforeEach
    void initializeBook(){
        book=new Book();
    }

    @Test
    void setBookIdTest(){
        book.setBookId(1);
        assertThat(book.getBookId()).isEqualTo(1);
    }

    @Test
    void setBookTitleTest(){
        book.setTitle("N or M?");
        assertThat(book.getTitle()).isEqualTo("N or M?");
    }

    @Test
    void setBookAuthor(){
        book.setAuthor("Agatha Christie");
        assertThat(book.getAuthor()).isEqualTo("Agatha Christie");
    }

    @Test
    void setIsbnTest(){
        book.setIsbn(BigInteger.valueOf(123456L));
        assertThat(book.getIsbn()).isEqualTo(BigInteger.valueOf(123456L));
    }


}
