package com.endava.bookrental.servicetests;

import com.endava.bookrental.exceptions.BookNotFoundException;
import com.endava.bookrental.exceptions.BookOwnerRelationNotFoundException;
import com.endava.bookrental.exceptions.EmptyDatabaseException;
import com.endava.bookrental.models.Book;
import com.endava.bookrental.prototypes.BookPrototype;
import com.endava.bookrental.repositories.BookOwnerRepository;
import com.endava.bookrental.repositories.BookRepository;
import com.endava.bookrental.repositories.BorrowedBookRepository;
import com.endava.bookrental.repositories.WaitingListRepository;
import com.endava.bookrental.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookOwnerRepository bookOwnerRepository;

    @Mock
    private BorrowedBookRepository borrowedBookRepository;

    @Mock
    private WaitingListRepository waitingListRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;

    @BeforeEach
    void setUp(){
        book= BookPrototype.getBookPrototype();
    }

    @Test
    public void shouldThrowEmptyDatabaseException(){
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(EmptyDatabaseException.class,()->bookService.getAll());
        assertThrows(EmptyDatabaseException.class,()->bookService.deleteAllBooks());
        assertThrows(EmptyDatabaseException.class,()->bookService.getAvailableBooks());
        assertThrows(EmptyDatabaseException.class,()->bookService.getBookByTitleOrAuthor("title"));
    }

    @Test
    public void shouldThrowBookNotFoundException(){
        when(bookRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class,()->bookService.getBookById(1));
        assertThrows(BookNotFoundException.class,()->bookService.deleteBook(1));
    }

    @Test
    public void shouldThrowBookOwnerNotFoundException(){
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        assertThrows(BookOwnerRelationNotFoundException.class,()->bookService.deleteBook(1));
    }
}