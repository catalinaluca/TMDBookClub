package com.endava.bookrental.services;

import com.endava.bookrental.models.User;
import com.endava.bookrental.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long user_id){
       return userRepository.findById(user_id);
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }
    public void deleteUser(Long user_id){
        userRepository.deleteById(user_id);

    }

}
