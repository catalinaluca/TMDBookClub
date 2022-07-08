package com.endava.bookrental.controllers;

import com.endava.bookrental.exceptions.UserNotFoundException;
import com.endava.bookrental.exceptions.UsernameNullException;
import com.endava.bookrental.exceptions.UsernameTakenException;
import com.endava.bookrental.models.User;
import com.endava.bookrental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public Object getUserById(@PathVariable("id") Long user_id){
            try {
                return userService.getUserById(user_id);
            }catch (UserNotFoundException e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addUser(@RequestBody User user){
        try {
            return userService.addUser(user);
        }catch (UsernameNullException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (UsernameTakenException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/delete",method = RequestMethod.DELETE)
    public Object deleteAllUsers(){
       try{
           userService.deleteAllUsers();
           return new ResponseEntity<>("Users deleted successfully!",HttpStatus.ACCEPTED);
       }catch (Exception e){
           return new ResponseEntity<>("This database does not contain any users!",HttpStatus.NO_CONTENT);
       }
    }

    @RequestMapping(path = "/delete/{id}",method = RequestMethod.DELETE)
    public Object deleteUser(@PathVariable("id") Long user_id){
        try{
            userService.deleteUser(user_id);
            return new ResponseEntity<>("User deleted successfully!",HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("This user does not exist!",HttpStatus.NO_CONTENT);
        }
    }
}
