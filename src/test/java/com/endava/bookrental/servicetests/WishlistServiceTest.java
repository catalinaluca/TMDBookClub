package com.endava.bookrental.servicetests;

import com.endava.bookrental.exceptions.BookAlreadyOnWishlistException;
import com.endava.bookrental.exceptions.BookNotFoundException;
import com.endava.bookrental.exceptions.EmptyDatabaseException;
import com.endava.bookrental.exceptions.UserNotFoundException;
import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.User;
import com.endava.bookrental.models.WishList;
import com.endava.bookrental.prototypes.BookPrototype;
import com.endava.bookrental.prototypes.UserPrototype;
import com.endava.bookrental.prototypes.WishlistPrototype;
import com.endava.bookrental.repositories.BookRepository;
import com.endava.bookrental.repositories.UserRepository;
import com.endava.bookrental.repositories.WishlistRepository;
import com.endava.bookrental.services.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WishlistServiceTest {

    @InjectMocks
    private WishlistService wishlistService;

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    private WishList wishList;
    private User user;
    private Book book;

    @BeforeEach
    void setUp(){
        wishList= WishlistPrototype.getWishlistPrototype();
        user= UserPrototype.getUserPrototype();
        book= BookPrototype.getBookPrototype();
    }

    @Test
    public void shouldThrowEmptyDatabaseException() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertThrows(EmptyDatabaseException.class, () -> wishlistService.getAll());
        assertThrows(EmptyDatabaseException.class, () -> wishlistService.deleteAllWishes());
        assertThrows(EmptyDatabaseException.class, () -> wishlistService.getWishlistForUser(1));
    }

    @Test
    public void shouldThrowUserNotFound(){
        assertThrows(UserNotFoundException.class, () -> wishlistService.getWishlistForUser(1));
        assertThrows(UserNotFoundException.class, () -> wishlistService.addBookOnWishlist(1,1));
        assertThrows(UserNotFoundException.class, () -> wishlistService.deleteBookFromWishlist(1,1));
    }

    @Test
    public void shouldThrowBookNotFound(){
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertThrows(BookNotFoundException.class, () -> wishlistService.addBookOnWishlist(1,1));
        assertThrows(BookNotFoundException.class, () -> wishlistService.deleteBookFromWishlist(1,1));
    }

    @Test
    public void shouldThrowBookAlreadyInWishlistException() throws UserNotFoundException, EmptyDatabaseException {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(wishlistRepository.findWishListByBookId(1)).thenReturn(Optional.of(wishList));
        assertThrows(BookAlreadyOnWishlistException.class, () -> wishlistService.addBookOnWishlist(1,1));
    }
}
