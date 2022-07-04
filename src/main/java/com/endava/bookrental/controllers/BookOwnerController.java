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
    public Object getBooksForOwner(@PathVariable("id") Integer id){
        try {
            return bookOwnerService.getBooksForOwner(id);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(path = "/book/{id}",method = RequestMethod.GET)
    public Object getOwnerForBook(@PathVariable("id") Integer id){
        try {
            return bookOwnerService.getOwnerForBook(id);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
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

    @RequestMapping(path = "/delete/{id}",method = RequestMethod.DELETE)
    public Object deleteBookOwner(@PathVariable("id") Integer id){
        try {
            bookOwnerService.deleteBookOwner(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
