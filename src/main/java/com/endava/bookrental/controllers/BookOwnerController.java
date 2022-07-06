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
            return bookOwnerService.getBooksForOwner(id).isPresent()?
                    bookOwnerService.getBooksForOwner(id):
                        new ResponseEntity<>("This user does not own any books!",HttpStatus.NO_CONTENT);

    }

    @RequestMapping(path = "/book/{id}",method = RequestMethod.GET)
    public Object getOwnerForBook(@PathVariable("id") Integer id){

            return bookOwnerService.getOwnerForBook(id).isPresent()?
                    bookOwnerService.getOwnerForBook(id):
                        new ResponseEntity<>("This book does not have an owner or it does not exist at all!",HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public Object addBookForOwner(@RequestBody Book book, @RequestParam(name = "id") Integer id){
        try{
            return bookOwnerService.addBookForOwner(book,id);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/delete",method = RequestMethod.DELETE)
    public Object deleteAll(){
        try {
            bookOwnerService.deleteAll();
            return new ResponseEntity<>("Deleted successfully!",HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("Empty database!",HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(path = "/delete/{id}",method = RequestMethod.DELETE)
    public Object deleteBookOwner(@PathVariable("id") Integer id){
        try {
            bookOwnerService.deleteBookOwner(id);
            return new ResponseEntity<>("Book-owner relation deleted successfully!",HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("This book-owner relation does not exist!",HttpStatus.NO_CONTENT);
        }
    }
}
