package com.endava.bookrental.services;

import com.endava.bookrental.controllers.BookOwnerController;
import com.endava.bookrental.exceptions.*;
import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BorrowedBook;
import com.endava.bookrental.repositories.BookRepository;
import com.endava.bookrental.repositories.BorrowedBookRepository;
import com.endava.bookrental.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class BorrowedBookService {
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Autowired
    private BookOwnerService bookOwnerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    private void validateNotEmptyDatabase() throws EmptyDatabaseException {
        if (borrowedBookRepository.findAll().isEmpty()) throw new EmptyDatabaseException();
    }

    private void validateBookIdInBorrowedBooks(Integer id) throws BookNotFoundException {
        if(!borrowedBookRepository.getBookOwnerIdForBookId(id).isPresent() ||!bookRepository.findAll().contains(id))throw new BookNotFoundException();
    }

    private void validateUser(Integer userId) throws UserNotFoundException {
        if(!bookRepository.findAll().contains(userId))throw new UserNotFoundException();
    }

    public List<BorrowedBook> getAllBorrowedBooks() throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        return borrowedBookRepository.findAll();
    }


    public BorrowedBook borrowBook(Integer userId, Integer bookId, Integer period) throws BookNotFoundException, UserNotFoundException {
        validateBookIdInBorrowedBooks(bookId);
        validateUser(userId);
        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setUserId(userId);
        borrowedBook.setBookOwnerId(bookOwnerService.getBookOwnerIdByBookId(bookId).get());
        borrowedBook.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
        borrowedBook.setEndDate(Timestamp.valueOf(LocalDateTime.now().plusDays(period)));
        return borrowedBookRepository.save(borrowedBook);
    }

    public BorrowedBook extendPeriod(Integer rentingId) {
        BorrowedBook borrowedBook = borrowedBookRepository.findById(rentingId).get();
        borrowedBookRepository.deleteById(rentingId);
        borrowedBook.setEndDate(Timestamp.valueOf(borrowedBook.getEndDate().toLocalDateTime().plusDays(14)));
        return borrowedBookRepository.save(borrowedBook);
    }

    public List<Object> getOwnedBooks(Integer ownerId) throws EmptyDatabaseException, UserNotFoundException, BookOwnerRelationNotFoundException {
        validateNotEmptyDatabase();
        if (!userRepository.findById(Long.valueOf(ownerId)).isPresent()) throw new UserNotFoundException();
        List<Object> ownedBooks = borrowedBookRepository.getOwnedBooks(ownerId);
        if(ownedBooks.isEmpty())throw new BookOwnerRelationNotFoundException();
        return ownedBooks;
    }

    public List<Object> getRentedBooks(Integer userId) throws EmptyDatabaseException, RenterNotFoundException {
        validateNotEmptyDatabase();
        List<Object> borrowedBooks= borrowedBookRepository.getRentedBooks(userId);
        if(borrowedBooks.isEmpty())throw new RenterNotFoundException();
        return borrowedBooks;
    }

    public void deleteAllBorrowedBooks() throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        borrowedBookRepository.deleteAll();
    }

    public void deleteBookWithBookId(Integer id) throws BookNotFoundException {
        validateBookIdInBorrowedBooks(id);
        borrowedBookRepository.deleteById(borrowedBookRepository.getBookOwnerIdForBookId(id).get());
    }
}
