package com.endava.bookrental.servicetests;

import com.endava.bookrental.exceptions.*;
import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.User;
import com.endava.bookrental.models.WaitingList;
import com.endava.bookrental.prototypes.BookPrototype;
import com.endava.bookrental.prototypes.UserPrototype;
import com.endava.bookrental.prototypes.WaitingListPrototype;
import com.endava.bookrental.repositories.BookRepository;
import com.endava.bookrental.repositories.BorrowedBookRepository;
import com.endava.bookrental.repositories.UserRepository;
import com.endava.bookrental.repositories.WaitingListRepository;
import com.endava.bookrental.services.WaitingListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WaitingListServiceTest {

    @Mock
    private WaitingListRepository waitingListRepository;

    @Mock
    private BorrowedBookRepository borrowedBookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private WaitingListService waitingListService;

    private WaitingList waitingList;
    private User user;
    private Book book;

    @BeforeEach
    void setUp() {
        user = UserPrototype.getUserPrototype();
        book = BookPrototype.getBookPrototype();
        waitingList = WaitingListPrototype.getWaitingListPrototype();
    }

    @Test
    public void shouldThrowEmptyDatabaseException() {
        assertThrows(EmptyDatabaseException.class, () -> waitingListService.getAllWaiters());
        assertThrows(EmptyDatabaseException.class, () -> waitingListService.deleteAllWaiters());
    }

    @Test
    public void shouldThrowWaiterNotFoundException() {
        assertThrows(WaiterNotFoundException.class, () -> waitingListService.deleteWaiterWithId(1));
    }

    @Test
    public void shouldThrowUserNotFoundException() {
        assertThrows(UserNotFoundException.class, () -> waitingListService.addWaiter(1, 1));
    }

    @Test
    public void shouldThrowBookNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertThrows(BookNotFoundException.class, () -> waitingListService.addWaiter(1, 1));
    }

    @Test
    public void shouldThrowBorrowedBookNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        assertThrows(BorrowedBookNotFoundException.class, () -> waitingListService.addWaiter(1, 1));
    }
}