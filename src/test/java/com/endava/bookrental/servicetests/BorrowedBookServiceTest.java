package com.endava.bookrental.servicetests;

import com.endava.bookrental.exceptions.*;
import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BookOwner;
import com.endava.bookrental.models.BorrowedBook;
import com.endava.bookrental.models.User;
import com.endava.bookrental.repositories.BookOwnerRepository;
import com.endava.bookrental.repositories.BookRepository;
import com.endava.bookrental.repositories.BorrowedBookRepository;
import com.endava.bookrental.repositories.UserRepository;
import com.endava.bookrental.services.BookOwnerService;
import com.endava.bookrental.services.BorrowedBookService;
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
    private BookOwnerService bookOwnerService;

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
        book=new Book();
        book.setBookId(1);
        book.setTitle("title");
        book.setAuthor("author");
        book.setIsbn(BigInteger.valueOf(1234568));
        user=new User();
        user.setUsername("user");
        user.setUser_id(1L);
        user.setEncodedPassword("userPass");
        user.setEmail("user@gmail.com");
        user.setSurname("user");
        user.setName("user");
        bookOwner=new BookOwner();
        bookOwner.setBookId(1);
        bookOwner.setOwnerId(1);
        bookOwner.setBookOwnerId(1);
        borrowedBook=new BorrowedBook();
        borrowedBook.setBookOwnerId(1);
        borrowedBook.setUserId(1);
        borrowedBook.setRentingId(1);
        borrowedBook.setStartDate(Timestamp.valueOf(LocalDateTime.of(2022,11,2,23,43)));
        borrowedBook.setEndDate(Timestamp.valueOf(LocalDateTime.of(2022,11,2,23,43).plusDays(7)));
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
        assertThrows(BookNotFoundException.class,()->borrowedBookService.borrowBook(1,1,14));
        assertThrows(BookNotFoundException.class,()->borrowedBookService.deleteBookWithBookId(1));
    }

    @Test
    public void shouldThrowUserNotFoundException(){
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(borrowedBookRepository.findAll()).thenReturn(List.of(borrowedBook));
        assertThrows(UserNotFoundException.class,()->borrowedBookService.borrowBook(1,1,14));
        assertThrows(UserNotFoundException.class,()->borrowedBookService.getOwnedBooks(1));
    }

    @Test
    public void shouldThrowBookOwnerRelationNotFoundException(){
        when(borrowedBookRepository.findAll()).thenReturn(List.of(borrowedBook));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertThrows(BookOwnerRelationNotFoundException.class,()->borrowedBookService.getOwnedBooks(1));
    }

}