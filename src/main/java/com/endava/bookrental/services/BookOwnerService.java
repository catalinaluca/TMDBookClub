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
        if (bookOwner.isEmpty()) throw new BookOwnerRelationNotFoundException();
    }

    public List<BookOwner> getAllBooksOwners() throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        return bookOwnerRepository.findAll();
    }

    public List<BookOwner> getBooksForOwner(Integer id) throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        return bookOwnerRepository.getBooksForOwner(id);
    }

    public Optional<BookOwner> getOwnerForBook(Integer id) throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        return bookOwnerRepository.getOwnerForBook(id);
    }

    public Integer getBookOwnerIdByBookId(Integer id) throws EmptyDatabaseException, BookOwnerRelationNotFoundException {
        validateNotEmptyDatabase();
        Optional<BookOwner> bookOwner=bookOwnerRepository.getBookOwnerByBookId(id);
        validateBookOwner(bookOwner);
        return bookOwner.get().getBookOwnerId();
    }

    public Integer getBookOwnerIdByUserId(Long userId) throws EmptyDatabaseException, BookOwnerRelationNotFoundException {
        validateNotEmptyDatabase();
        List<Optional<BookOwner>> bookOwnerList=bookOwnerRepository.getBookOwnerByOwnerId(Math.toIntExact(userId));
        validateBookOwner(bookOwnerList.get(0));
        return bookOwnerList.get(0).get().getBookOwnerId();
    }
    public BookOwner addBookForOwner(Book book, Integer id){
        bookService.addBook(book);
        BookOwner bookOwner = new BookOwner();
        bookOwner.setBookId(book.getBookId());
        bookOwner.setOwnerId(id);
        return bookOwnerRepository.save(bookOwner);
    }

    public Optional<Book> getBookByBookOwnerId(Integer bookOwnerId) throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        return bookOwnerRepository.getBookByBookOwnerId(bookOwnerId);
    }

    public void deleteAll() throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        bookService.deleteAllBooks();
    }

    public void deleteBookOwner(Integer id) throws BookNotFoundException, BookOwnerRelationNotFoundException {
        validateBookOwner(bookOwnerRepository.findById(id));
        bookService.deleteBook(bookOwnerRepository.findById(id).get().getBookId());
    }
}

