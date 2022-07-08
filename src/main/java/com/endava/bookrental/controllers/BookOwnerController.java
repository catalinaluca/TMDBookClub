package com.endava.bookrental.controllers;

import com.endava.bookrental.exceptions.BookNotFoundException;
import com.endava.bookrental.exceptions.BookOwnerRelationNotFoundException;
import com.endava.bookrental.exceptions.EmptyDatabaseException;
import com.endava.bookrental.models.Book;
import com.endava.bookrental.services.BookOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("books/owners")
public class BookOwnerController {
    @Autowired
    private BookOwnerService bookOwnerService;

    @RequestMapping(method = RequestMethod.GET)
    public Object getAllBooksOwners() {
        try {
            return bookOwnerService.getAllBooksOwners();
        } catch (EmptyDatabaseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/owner", method = RequestMethod.GET)
    public Object getBooksForOwner(@RequestParam(name = "ownerId") Integer id) {
        return bookOwnerService.getBooksForOwner(id).isEmpty() ?
                new ResponseEntity<>("This user does not own any books!", HttpStatus.NO_CONTENT) :
                bookOwnerService.getBooksForOwner(id);

    }

    @RequestMapping(path = "/book", method = RequestMethod.GET)
    public Object getOwnerForBook(@RequestParam(name = "bookId") Integer id) {
        return bookOwnerService.getOwnerForBook(id).isPresent() ?
                bookOwnerService.getOwnerForBook(id) :
                new ResponseEntity<>("This book does not have an owner or it does not exist at all!", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Object addBookForOwner(@RequestBody Book book, @RequestParam(name = "id") Integer id) {
        try {
            return bookOwnerService.addBookForOwner(book, id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public Object deleteAll() {
        try {
            bookOwnerService.deleteAll();
            return new ResponseEntity<>("Deleted successfully!", HttpStatus.ACCEPTED);
        } catch (EmptyDatabaseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/delete/bookOwner", method = RequestMethod.DELETE)
    public Object deleteBookOwner(@RequestParam(name = "bookOwnerId") Integer id) {
        try {
            bookOwnerService.deleteBookOwner(id);
            return new ResponseEntity<>("Book-owner relation deleted successfully!", HttpStatus.ACCEPTED);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (BookOwnerRelationNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
