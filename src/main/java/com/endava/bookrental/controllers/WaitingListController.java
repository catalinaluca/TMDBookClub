package com.endava.bookrental.controllers;

import com.endava.bookrental.services.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books/waitingList")
public class WaitingListController {
    @Autowired
    private WaitingListService waitingListService;

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public Object addWaiter(@RequestParam(name = "userId") Integer userId,@RequestParam(name = "bookId") Integer bookId){
       try{
           return waitingListService.addWaiter(userId,bookId);
       }catch (Exception e){
           return new ResponseEntity<>("This book is either available for renting or non-existent.",HttpStatus.BAD_REQUEST);
       }
    }
}
