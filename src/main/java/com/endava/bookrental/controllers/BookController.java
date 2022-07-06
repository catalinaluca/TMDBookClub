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
    public Object getBookById(@PathVariable("id") Integer id){
            return bookService.getBookById(id).isPresent()?bookService.getBookById(id).get():new ResponseEntity<>("This book does not exist!",HttpStatus.NO_CONTENT);
    }
    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @RequestMapping(path = "/delete/{id}",method = RequestMethod.DELETE)
    public Object deleteBook(@PathVariable("id") Integer id){
        try{
            bookService.deleteBook(id);
            return new ResponseEntity<>("Book deleted successfully!",HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("This book does not exist!",HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(path = "/delete",method = RequestMethod.DELETE)
    public Object deleteAllBooks(){
        try{
            bookService.deleteAllBooks();
            return new ResponseEntity<>("Deleted successfully!",HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("Empty database!",HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(path = "/available",method = RequestMethod.GET)
    public List<Object> getAvailableBooks(){
        List<Object> availableList=bookService.getAvailableBooks();
        if(!availableList.isEmpty()){
            return availableList;
        }
        availableList.add(new ResponseEntity<>("There are not any available books for now!",HttpStatus.NO_CONTENT));
        return availableList;
    }

    @RequestMapping(path = "/searchByTitleOrAuthor",method = RequestMethod.GET)
    public List<Object> getBookByTitleOrAuthor(@RequestParam(name = "title",required = false) String title, @RequestParam(name = "author",required = false) String author){
        List<Object> searchList=bookService.getBookByTitleOrAuthor(title,author);
        if(!searchList.isEmpty()){
            return searchList;
        }
        searchList.add(new ResponseEntity<>("There are no books that satisfy your search!",HttpStatus.NO_CONTENT));
        return searchList;
    }

}
