package com.endava.bookrental.controllers;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public Book getBook(@PathVariable("id") Integer id){
        return bookService.getBook(id);
    }

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @RequestMapping(path = "/delete/{id}",method = RequestMethod.DELETE)
    public Object deleteBook(@PathVariable("id") Integer id){
        try{
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(path = "/delete",method = RequestMethod.DELETE)
    public Object deleteAllBooks(){
        try{
            bookService.deleteAllBooks();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
