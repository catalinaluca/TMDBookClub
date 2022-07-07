package com.endava.bookrental.modeltests;

import com.endava.bookrental.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserModelTest {
    private static User user;
    @BeforeEach
    void initializeUser(){
        user=new User();
    }
    @Test
    void setUserIdTest(){
        user.setUser_id(1L);
        assertThat(user.getUserId()).isEqualTo(1L);
    }

    @Test
    void setUsernameTest(){
        user.setUsername("useRname1");
        assertThat(user.getUsername()).isEqualTo("useRname1");
    }

    @Test
    void setNameTest(){
        user.setName("Name");
        assertThat(user.getName()).isEqualTo("Name");
    }
    @Test
    void setSurnameTest(){
        user.setSurname("Surname");
        assertThat(user.getSurname()).isEqualTo("Surname");
    }

    @Test
    void setEmailTest(){
        user.setEmail("Name1@gmail.com");
        assertThat(user.getEmail()).isEqualTo("Name1@gmail.com");
    }

    @Test
    void setPasswordTest(){
        user.setEncodedPassword("name123");
        assertThat(user.getEncodedPassword()).isEqualTo("name123");
    }
}
