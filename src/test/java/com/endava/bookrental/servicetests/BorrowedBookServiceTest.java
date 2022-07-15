package com.endava.bookrental.servicetests;

import com.endava.bookrental.exceptions.*;
import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BookOwner;
import com.endava.bookrental.models.BorrowedBook;
import com.endava.bookrental.models.User;
import com.endava.bookrental.prototypes.BookOwnerPrototype;
import com.endava.bookrental.prototypes.BookPrototype;
import com.endava.bookrental.prototypes.BorrowedBookPrototype;
import com.endava.bookrental.prototypes.UserPrototype;
import com.endava.bookrental.repositories.BookOwnerRepository;
import com.endava.bookrental.repositories.BookRepository;
import com.endava.bookrental.repositories.BorrowedBookRepository;
import com.endava.bookrental.repositories.UserRepository;
import com.endava.bookrental.services.BookOwnerService;
import com.endava.bookrental.services.BorrowedBookService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BorrowedBookServiceTest {

    @Mock
    private BorrowedBookRepository borrowedBookRepository;

    @Mock
    private BookOwnerRepository bookOwnerRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BorrowedBookService borrowedBookService;

    private BorrowedBook borrowedBook;

    private Book book;

    private User user;

    private BookOwner bookOwner;

    @BeforeEach
    void setUp(){
        book= BookPrototype.getBookPrototype();
        user= UserPrototype.getUserPrototype();
        bookOwner= BookOwnerPrototype.getBookOwnerPrototype();
        borrowedBook= BorrowedBookPrototype.getBorrowedBookPrototype();
    }

    @Test
    public void shouldThrowEmptyDatabaseException(){
        when(borrowedBookRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(EmptyDatabaseException.class,()->borrowedBookService.getAllBorrowedBooks());
        assertThrows(EmptyDatabaseException.class,()->borrowedBookService.getOwnedBooks(1));
        assertThrows(EmptyDatabaseException.class,()->borrowedBookService.getRentedBooks(1));
        assertThrows(EmptyDatabaseException.class,()->borrowedBookService.deleteAllBorrowedBooks());
    }

    @Test
    public void shouldThrowRenterNotFoundException(){
        when(borrowedBookRepository.findAll()).thenReturn(List.of(borrowedBook));
        assertThrows(RenterNotFoundException.class,()->borrowedBookService.getRentedBooks(1));
    }

    @Test
    public void shouldThrowBookNotFoundException(){
        assertThrows(BookNotFoundException.class,()->borrowedBookService.borrowBook(1,1,1,14));
        assertThrows(BookNotFoundException.class,()->borrowedBookService.deleteBookWithBookId(1));
    }

    @Test
    public void shouldThrowUserNotFoundException(){
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(borrowedBookRepository.findAll()).thenReturn(List.of(borrowedBook));
        assertThrows(UserNotFoundException.class,()->borrowedBookService.borrowBook(1,1,1,14));
        assertThrows(UserNotFoundException.class,()->borrowedBookService.getOwnedBooks(1));
    }

    @Test
    public void shouldThrowBookOwnerRelationNotFoundException(){
        when(borrowedBookRepository.findAll()).thenReturn(List.of(borrowedBook));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        assertThrows(BookOwnerRelationNotFoundException.class,()->borrowedBookService.getOwnedBooks(1));
        assertThrows(BookOwnerRelationNotFoundException.class,()->borrowedBookService.borrowBook(1,1,1,14));
    }

    @Test
    public void shouldThrowIllegalArgumentException(){
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(borrowedBookRepository.findBorrowedBookByBookOwnerId(1)).thenReturn(Optional.of(borrowedBook));
        when(bookOwnerRepository.getBookByBookOwnerId(1)).thenReturn(Optional.of(book));
        assertThrows(IllegalArgumentException.class,()->borrowedBookService.borrowBook(1,1,1,14));
    }

}