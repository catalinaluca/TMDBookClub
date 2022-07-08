package com.endava.bookrental.services;

import com.endava.bookrental.exceptions.UserNotFoundException;
import com.endava.bookrental.exceptions.UsernameNullException;
import com.endava.bookrental.exceptions.UsernameTakenException;
import com.endava.bookrental.models.User;
import com.endava.bookrental.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private void validateUsername(User user) throws UsernameNullException{
        if(user.getUsername()==null ||user.getUsername().isEmpty() )throw new UsernameNullException();
    }

    private void validateUser(User user) throws UserNotFoundException{
        if(user.equals(null))throw new UserNotFoundException();
    }

    private void validateUniqueUsername(String username) throws UsernameTakenException {
        if(userRepository.findByUsername(username).isPresent())throw new UsernameTakenException();
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long user_id) throws UserNotFoundException{
        Optional<User> user=userRepository.findById(user_id);
        validateUser(user.get());
        return user;
    }

    public User addUser(User user) throws UsernameNullException,UsernameTakenException{
        validateUsername(user);
        validateUniqueUsername(user.getUsername());
        return userRepository.save(user);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }
    public void deleteUser(Long user_id) throws UserNotFoundException{
        Optional<User> user=userRepository.findById(user_id);
        validateUser(user.get());
        userRepository.deleteById(user_id);
    }

}
