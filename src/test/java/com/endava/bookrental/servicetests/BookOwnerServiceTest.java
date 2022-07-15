package com.endava.bookrental.servicetests;

import com.endava.bookrental.exceptions.BookNotFoundException;
import com.endava.bookrental.exceptions.BookOwnerRelationNotFoundException;
import com.endava.bookrental.exceptions.EmptyDatabaseException;
import com.endava.bookrental.models.BookOwner;
import com.endava.bookrental.prototypes.BookOwnerPrototype;
import com.endava.bookrental.repositories.BookOwnerRepository;
import com.endava.bookrental.repositories.BookRepository;
import com.endava.bookrental.services.BookOwnerService;
import com.endava.bookrental.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookOwnerServiceTest {

    @Mock
    private BookOwnerRepository bookOwnerRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookOwnerService bookOwnerService;

    private BookOwner bookOwner;

    @BeforeEach
    void setUp(){
        bookOwner= BookOwnerPrototype.getBookOwnerPrototype();
    }

    @Test
    public void shouldThrowEmptyDatabaseException(){
        when(bookOwnerRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(EmptyDatabaseException.class,()->bookOwnerService.getAllBooksOwners());
        assertThrows(EmptyDatabaseException.class,()->bookOwnerService.deleteAll());
        assertThrows(EmptyDatabaseException.class,()->bookOwnerService.getBookOwnerIdByBookId(1));
        assertThrows(EmptyDatabaseException.class,()->bookOwnerService.getBooksForOwner(1));
        assertThrows(EmptyDatabaseException.class,()->bookOwnerService.getOwnerForBook(1));
        assertThrows(EmptyDatabaseException.class,()->bookOwnerService.getBookOwnerIdByUserId(1L));
    }

    @Test
    public void shouldThrowBookOwnerRelationNotFoundException(){
        when(bookOwnerRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(BookOwnerRelationNotFoundException.class,()->bookOwnerService.deleteBookOwner(1));
    }
}