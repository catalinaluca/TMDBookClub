package com.endava.bookrental.controllers;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BookOwner;
import com.endava.bookrental.services.BookOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books/owners")
public class BookOwnerController {
    @Autowired
    private BookOwnerService bookOwnerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookOwner> getAllBooksOwners(){
        return bookOwnerService.getAllBooksOwners();
    }

    @RequestMapping(path = "/owner/{id}",method = RequestMethod.GET)
    public BookOwner getBooksForOwner(@PathVariable("id") Integer id){
        return bookOwnerService.getBooksForOwner(id);
    }

    @RequestMapping(path = "/book/{id}",method = RequestMethod.GET)
    public BookOwner getOwnerForBook(@PathVariable("id") Integer id){
        return bookOwnerService.getOwnerForBook(id);
    }

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public Object addBookForOwner(@RequestBody Book book, @PathVariable("id") Integer id){
        try{
            return bookOwnerService.addBookForOwner(book,id);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/delete",method = RequestMethod.DELETE)
    public Object deleteAll(){
        try {
            bookOwnerService.deleteAll();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
