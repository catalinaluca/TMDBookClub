package com.endava.bookrental.servicetests;

import com.endava.bookrental.exceptions.EmptyDatabaseException;
import com.endava.bookrental.exceptions.UserNotFoundException;
import com.endava.bookrental.exceptions.UsernameNullException;
import com.endava.bookrental.exceptions.UsernameTakenException;
import com.endava.bookrental.models.User;
import com.endava.bookrental.prototypes.UserPrototype;
import com.endava.bookrental.repositories.UserRepository;
import com.endava.bookrental.services.BookOwnerService;
import com.endava.bookrental.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookOwnerService bookOwnerService;

    @InjectMocks
    private UserService userService;

    private User user;
    @BeforeEach
    void setUp() {
        user= UserPrototype.getUserPrototype();
    }

    @Test
    public void shouldThrowEmptyDatabaseException(){
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(EmptyDatabaseException.class,()->userService.getUsers());
        assertThrows(EmptyDatabaseException.class,()->userService.deleteAllUsers());
    }

    @Test
    public void shouldThrowUserNotFoundException(){
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->userService.getUserById(1L));
        assertThrows(UserNotFoundException.class,()->userService.deleteUser(1L));
    }

    @Test
    public void shouldThrowUsernameNullException(){
        user.setUsername("");
        assertThrows(UsernameNullException.class,()->userService.addUser(user));
    }

    @Test
    public void shouldThrowUsernameTakenException(){
        when(userRepository.findByUsername("yonpop12")).thenReturn(Optional.of(user));
        assertThrows(UsernameTakenException.class,()->userService.addUser(user));
    }

}