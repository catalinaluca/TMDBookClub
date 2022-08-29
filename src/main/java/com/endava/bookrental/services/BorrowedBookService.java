package com.endava.bookrental.services;

import com.endava.bookrental.controllers.BookOwnerController;
import com.endava.bookrental.exceptions.*;
import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BorrowedBook;
import com.endava.bookrental.repositories.BookOwnerRepository;
import com.endava.bookrental.repositories.BookRepository;
import com.endava.bookrental.repositories.BorrowedBookRepository;
import com.endava.bookrental.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BorrowedBookService {
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Autowired
    private BookOwnerRepository bookOwnerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    private void validateNotEmptyDatabase() throws EmptyDatabaseException {
        if (borrowedBookRepository.findAll().isEmpty()) throw new EmptyDatabaseException();
    }

    private void validateBookIdInBorrowedBooks(Integer id) throws BookNotFoundException {
        if(!borrowedBookRepository.findById(borrowedBookRepository.findBorrowedBookByBookOwnerId(id).get().getBookOwnerId()).isPresent())throw new BookNotFoundException();
    }

    private void validateBookOwnerId(Integer bookOwnerId) throws EmptyDatabaseException, BookOwnerRelationNotFoundException {
        if(bookOwnerRepository.getBookByBookOwnerId(bookOwnerId).isEmpty())throw new BookOwnerRelationNotFoundException();
    }

    private void validateBookInBooks(Integer id) throws BookNotFoundException{
        if(!bookRepository.findById(id).isPresent())throw new BookNotFoundException();
    }

    private void validateNotOwnerOfBook(Integer userId,Integer bookId) throws BorrowOwnBookException{
        if(bookOwnerRepository.getOwnerForBook(bookId).get().getOwnerId().equals(userId))throw new BorrowOwnBookException();
    }

    private void validateAvailable(Integer bookId) throws BookNotAvailableException{
        if(borrowedBookRepository.findBorrowedBookByBookOwnerId(bookOwnerRepository.getBookOwnerByBookId(bookId).get().getBookOwnerId()).isPresent())throw new BookNotAvailableException();
    }

    private void validateUser(Integer userId) throws UserNotFoundException {
        if(!userRepository.findById(Long.valueOf(userId)).isPresent())throw new UserNotFoundException();
    }

    public List<BorrowedBook> getAllBorrowedBooks() throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        return borrowedBookRepository.findAll();
    }


    public BorrowedBook borrowBook(Integer userId,Integer bookId, Integer period) throws BookNotFoundException, UserNotFoundException, IllegalArgumentException, BorrowOwnBookException, BookNotAvailableException {
        validateBookInBooks(bookId);
        validateAvailable(bookId);
        validateUser(userId);
        validateNotOwnerOfBook(userId,bookId);
        Integer bookOwnerId=bookOwnerRepository.getBookOwnerByBookId(bookId).get().getBookOwnerId();
        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setUserId(userId);
        borrowedBook.setBookOwnerId(bookOwnerId);
        borrowedBook.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
        borrowedBook.setEndDate(Timestamp.valueOf(LocalDateTime.now().plusDays(period)));
        borrowedBook.setExtended(false);
        return borrowedBookRepository.save(borrowedBook);
    }

    public BorrowedBook extendPeriod(Integer rentingId) throws ExtendedOnceException {
        BorrowedBook borrowedBook = borrowedBookRepository.findById(rentingId).get();
        if(borrowedBook.isExtended())throw new ExtendedOnceException();
        borrowedBookRepository.deleteById(rentingId);
        borrowedBook.setEndDate(Timestamp.valueOf(borrowedBook.getEndDate().toLocalDateTime().plusDays(14)));
        borrowedBook.setExtended(true);
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
        validateBookInBooks(id);
        validateBookIdInBorrowedBooks(id);
        borrowedBookRepository.deleteById(borrowedBookRepository.getBookOwnerIdForBookId(id).get());
    }
}
