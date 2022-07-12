package com.endava.bookrental.servicetests;

import com.endava.bookrental.exceptions.EmptyDatabaseException;
import com.endava.bookrental.exceptions.UserNotFoundException;
import com.endava.bookrental.exceptions.UsernameNullException;
import com.endava.bookrental.exceptions.UsernameTakenException;
import com.endava.bookrental.models.User;
import com.endava.bookrental.repositories.UserRepository;
import com.endava.bookrental.services.BookOwnerService;
import com.endava.bookrental.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookOwnerService bookOwnerService;

    @InjectMocks
    private UserService userService;


    @BeforeEach
    void setUp() {
    }

    @Test
    public void throwsEmptyDatabaseException(){
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(EmptyDatabaseException.class,()->userService.getUsers());
    }

    @Test
    public void throwsUserNotFoundException(){
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->userService.getUserById(1L));
    }

    @Test
    public void throwsUsernameNullException(){
        User user=new User();
        assertThrows(UsernameNullException.class,()->userService.addUser(user));
    }

    @Test
    public void throwsUsernameTakenException(){
      //  when(userRepository.findById(1L).get().getUsername()).thenReturn("user");
        User user=new User();
        user.setUser_id(1L);
        user.setUsername("user");

            when(userRepository.save(user)).thenReturn(user);
            assertThrows(UsernameTakenException.class,()->userService.addUser(user));

    }

    @AfterEach
    void tearDown() {
    }

}