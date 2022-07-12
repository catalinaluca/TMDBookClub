package com.endava.bookrental.services;

import com.endava.bookrental.exceptions.BookNotFoundException;
import com.endava.bookrental.exceptions.BookOwnerRelationNotFoundException;
import com.endava.bookrental.exceptions.EmptyDatabaseException;
import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BookOwner;
import com.endava.bookrental.repositories.BookOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookOwnerService {
    @Autowired
    private BookOwnerRepository bookOwnerRepository;
    @Autowired
    private BookService bookService;


    private void validateNotEmptyDatabase() throws EmptyDatabaseException {
        if (bookOwnerRepository.findAll().isEmpty()) throw new EmptyDatabaseException();
    }

    private void validateBookOwner(Optional<BookOwner> bookOwner) throws BookOwnerRelationNotFoundException {
        if (!bookOwner.isPresent()) throw new BookOwnerRelationNotFoundException();
    }

    public List<BookOwner> getAllBooksOwners() throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        return bookOwnerRepository.findAll();
    }

    public List<BookOwner> getBooksForOwner(Integer id) {
        return bookOwnerRepository.getBooksForOwner(id);
    }

    public Optional<BookOwner> getOwnerForBook(Integer id) {
        return bookOwnerRepository.getOwnerForBook(id);
    }

    public Optional<Integer> getBookOwnerIdByBookId(Integer id) {
        return bookOwnerRepository.getBookOwnerIdByBookId(id);
    }

    public Optional<Integer> getBookOwnerIdByUserId(Long userId){
       return bookOwnerRepository.getBookOwnerIdByUserId(Math.toIntExact(userId));
    }
    public BookOwner addBookForOwner(Book book, Integer id) {
        bookService.addBook(book);
        BookOwner bookOwner = new BookOwner();
        bookOwner.setBookId(book.getBookId());
        bookOwner.setOwnerId(id);
        return bookOwnerRepository.save(bookOwner);
    }

    public void deleteAll() throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        bookService.deleteAllBooks();
    }

    public void deleteBookOwner(Integer id) throws BookNotFoundException, BookOwnerRelationNotFoundException {
        if (bookOwnerRepository.findById(id).isPresent()) {
            bookService.deleteBook(bookOwnerRepository.findById(id).get().getBookId());
            validateBookOwner(bookOwnerRepository.findById(id));
        } else throw new BookNotFoundException();

    }
}

