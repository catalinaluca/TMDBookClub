package com.endava.bookrental.controllers;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BorrowedBook;
import com.endava.bookrental.services.BorrowedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books/borrowed")
public class BorrowedBookController {
    @Autowired
    private BorrowedBookService borrowedBookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BorrowedBook> getAllBorrowedBooks() {
        return borrowedBookService.getAllBorrowedBooks();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Object getBorrowedBooksForUserId(@PathVariable("id") Integer id) {
        try {
            return borrowedBookService.getBorrowedBooksForUserId(id);
        } catch (Exception e) {
            return new ResponseEntity<>("This user does not have any borrowed books!", HttpStatus.BAD_REQUEST);
        }

    }

    public List<Integer> getPeriods() {
        List<Integer> periods = new ArrayList<>();
        periods.add(7);
        periods.add(14);
        periods.add(21);
        periods.add(30);
        return periods;
    }

    @RequestMapping(path = "/borrow", method = RequestMethod.POST)
    public Object borrowBook(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "bookId") Integer bookId, @RequestParam(name = "period") Integer period) {
        if (!getPeriods().contains(period)) {
            return new ResponseEntity<>("The period that you gave is not accepted!", HttpStatus.BAD_REQUEST);
        }
        return borrowedBookService.borrowBook(userId, bookId, period);
    }

    @RequestMapping(path = "/extend", method = RequestMethod.POST)
    public Object extendPeriod(@RequestParam(name = "rentingId") Integer rentingId) {
        try {
            return borrowedBookService.extendPeriod(rentingId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(path = "/myBooks", method = RequestMethod.GET)
    public List<Object> getBooksOwned(@RequestParam(name = "ownerId") Integer ownerId) {
        List<Object> ownedList=borrowedBookService.getBooksOwned(ownerId);
        if(!ownedList.isEmpty()){
            return ownedList;
        }
        ownedList.add(new ResponseEntity<>("This user does not own any books!",HttpStatus.BAD_REQUEST));
        return ownedList;
    }

    @RequestMapping(path = "/rented", method = RequestMethod.GET)
    public List<Object> getRentedBooks(@RequestParam(name = "userId") Integer userId) {
        List<Object> rentedList=borrowedBookService.getRentedBooks(userId);
        if(!rentedList.isEmpty()){
            return rentedList;
        }
        rentedList.add(new ResponseEntity<>("This user has not rented any books!",HttpStatus.BAD_REQUEST));
        return rentedList;
    }
}
