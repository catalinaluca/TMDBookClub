package com.endava.bookrental.services;

import com.endava.bookrental.exceptions.*;
import com.endava.bookrental.models.Role;
import com.endava.bookrental.models.User;
import com.endava.bookrental.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;
    @Autowired
    private BookOwnerService bookOwnerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private void validateUsername(User user) throws UsernameNullException{
        if(user.getUsername()==null ||user.getUsername().isEmpty() )throw new UsernameNullException();
    }

    private void validateUser(Optional<User> user) throws UserNotFoundException{
        if(!user.isPresent())throw new UserNotFoundException();
    }

    private void validateNotEmptyDatabase() throws EmptyDatabaseException{
        if(userRepository.findAll().isEmpty())throw new EmptyDatabaseException();
    }

    private void validateUniqueUsername(String username) throws UsernameTakenException {
        if(userRepository.findByUsername(username).isPresent())throw new UsernameTakenException();
    }
    public List<User> getUsers() throws EmptyDatabaseException{
        validateNotEmptyDatabase();
        return userRepository.findAll();
    }

    public User getUserById(Long user_id) throws UserNotFoundException{
        Optional<User> user=userRepository.findById(user_id);
        validateUser(user);
        return user.get();
    }


    public User addUser(User user) throws UsernameNullException,UsernameTakenException{
        validateUsername(user);
        validateUniqueUsername(user.getUsername());
        return userRepository.save(user);
    }
    public User addUser(String username, String password, String email, String name, String surname) throws UsernameTakenException {
        validateUniqueUsername(username);
        User user=new User();
        user.setUsername(username);
        user.setEncodedPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        return userRepository.save(user);
    }

        public void deleteAllUsers() throws EmptyDatabaseException{
        validateNotEmptyDatabase();
        bookOwnerService.deleteAll();
        userRepository.deleteAll();
    }
    public void deleteUser(Long user_id) throws UserNotFoundException{
        Optional<User> user=userRepository.findById(user_id);
        validateUser(user);
        try {
            bookOwnerService.getBookOwnerIdByUserId(user_id);
            userRepository.deleteById(user_id);
        } catch (Exception ignored) {}

    }

}
