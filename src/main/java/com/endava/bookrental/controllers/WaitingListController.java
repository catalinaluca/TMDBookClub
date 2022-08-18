package com.endava.bookrental.controllers;

import com.endava.bookrental.exceptions.*;
import com.endava.bookrental.services.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "http://localhost:3000",
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT},
        maxAge = 3600)
@RequestMapping("books/waitingList")
public class WaitingListController {
    @Autowired
    private WaitingListService waitingListService;

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public Object addWaiter(@RequestParam(name = "userId") Integer userId,@RequestParam(name = "bookId") Integer bookId){
       try{
           return waitingListService.addWaiter(userId,bookId);
       }catch (UserNotFoundException e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }catch (BookNotFoundException e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }catch (BorrowedBookNotFoundException e){
           return new ResponseEntity<>("This book is available for renting.",HttpStatus.NOT_FOUND);
       }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object getWaiters(){
        try {
            return waitingListService.getAllWaiters();
        }catch (EmptyDatabaseException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/delete",method = RequestMethod.DELETE)
    public Object deleteAllWaiters() {
        try {
           waitingListService.deleteAllWaiters();
           return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (EmptyDatabaseException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/delete/waiter",method = RequestMethod.DELETE)
    public Object deleteWaiterWithId(@RequestParam(name = "waiterId") Integer waiterId){
        try {
            waitingListService.deleteWaiterWithId(waiterId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (WaiterNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
