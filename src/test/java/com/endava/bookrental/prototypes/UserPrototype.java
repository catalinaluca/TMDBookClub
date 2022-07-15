package com.endava.bookrental.prototypes;

import com.endava.bookrental.models.User;

public class UserPrototype {
    public static User getUserPrototype(){
        User userPrototype = new User();
        userPrototype.setUser_id(1L);
        userPrototype.setName("Ion");
        userPrototype.setSurname("Pop");
        userPrototype.setEmail("ionpop@gmail.com");
        userPrototype.setUsername("yonpop12");
        userPrototype.setEncodedPassword("yonybarosanu");

        return userPrototype;
    }
}
