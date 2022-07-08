package com.endava.bookrental.controllers;

import com.endava.bookrental.exceptions.BookNotFoundException;
import com.endava.bookrental.exceptions.EmptyDatabaseException;
import com.endava.bookrental.models.Book;
import com.endava.bookrental.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public Object getAll() {
        try {
            return bookService.getAll();
        } catch (EmptyDatabaseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(path = "/getBook", method = RequestMethod.GET)
    public Object getBookById(@RequestParam(name = "bookId") Integer id) {
        try {
            return bookService.getBookById(id);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Object addBook(@RequestBody Book book) {
        try {
            return bookService.addBook(book);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/delete/book", method = RequestMethod.DELETE)
    public Object deleteBook(@RequestParam(name = "bookId") Integer id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>("Book deleted successfully!", HttpStatus.ACCEPTED);
        }catch (BookNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public Object deleteAllBooks() {
        try {
            bookService.deleteAllBooks();
            return new ResponseEntity<>("Deleted successfully!", HttpStatus.ACCEPTED);
        } catch (EmptyDatabaseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/available", method = RequestMethod.GET)
    public List<Object> getAvailableBooks() {
        List<Object> availableList = null;
        try {
            availableList = new ArrayList<>();
            availableList = bookService.getAvailableBooks();
            if (!availableList.isEmpty()) {
                return availableList;
            }
        } catch (EmptyDatabaseException e) {
            availableList.add(new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT));
            return availableList;
        }
        availableList.add(new ResponseEntity<>("There are not any available books for now!", HttpStatus.NO_CONTENT));
        return availableList;
    }

    @RequestMapping(path = "/searchByTitleOrAuthor", method = RequestMethod.GET)
    public List<Object> getBookByTitleOrAuthor(@RequestParam(name = "title", required = false) String title, @RequestParam(name = "author", required = false) String author) {
        List<Object> searchList = null;
        try {
            searchList = new ArrayList<>();
            searchList = bookService.getBookByTitleOrAuthor(title, author);
            if (!searchList.isEmpty()) {
                return searchList;
            }
        } catch (EmptyDatabaseException e) {
            searchList.add(new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT));
            return searchList;
        }
        if (!searchList.isEmpty()) {
            return searchList;
        }
        searchList.add(new ResponseEntity<>("There are no books that satisfy your search!", HttpStatus.NO_CONTENT));
        return searchList;
    }

}
