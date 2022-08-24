package com.endava.bookrental.services;

import com.endava.bookrental.exceptions.BookNotFoundException;
import com.endava.bookrental.exceptions.BookOwnerRelationNotFoundException;
import com.endava.bookrental.exceptions.EmptyDatabaseException;
import com.endava.bookrental.models.Book;
import com.endava.bookrental.repositories.BookOwnerRepository;
import com.endava.bookrental.repositories.BookRepository;
import com.endava.bookrental.repositories.BorrowedBookRepository;
import com.endava.bookrental.repositories.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookOwnerRepository bookOwnerRepository;

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Autowired
    private WaitingListRepository waitingListRepository;

    private void validateBook(Optional<Book> book) throws BookNotFoundException{
        if(!book.isPresent())throw new BookNotFoundException();
    }

    private void validateNotEmptyDatabase() throws EmptyDatabaseException{
        if(bookRepository.findAll().isEmpty())throw new EmptyDatabaseException();
    }

    public List<Book> getAll() throws EmptyDatabaseException{
        validateNotEmptyDatabase();
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) throws BookNotFoundException{
        Optional<Book> bookFound=bookRepository.findById(id);
        validateBook(bookFound);
        return bookFound.get();
    }

    public Optional<Object> getDetailsByBookId(Integer bookId) throws BookNotFoundException{
        validateBook(bookRepository.findById(bookId));
        return bookRepository.getDetailsByBookId(bookId);
    }

    public Book addBook(Book book){
         return  bookRepository.findByTitleAuthorISBN(book.getTitle(),book.getAuthor(),book.getIsbn()).isPresent()?
                    bookRepository.findByTitleAuthorISBN(book.getTitle(),book.getAuthor(),book.getIsbn()).get():
                        bookRepository.save(book);
    }

    public Book editBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Integer id) throws BookNotFoundException, BookOwnerRelationNotFoundException {
        validateBook(bookRepository.findById(id));
        bookRepository.deleteById(id);
        if(bookOwnerRepository.getBookOwnerByBookId(id).isPresent()) {
            bookOwnerRepository.deleteById(bookOwnerRepository.getBookOwnerByBookId(id).get().getBookOwnerId());
        }else throw new BookOwnerRelationNotFoundException();
        Integer bookOwnerId=bookOwnerRepository.getBookOwnerByBookId(id).get().getBookOwnerId();
        if(borrowedBookRepository.findBorrowedBookByBookOwnerId(bookOwnerId).isPresent()){
            borrowedBookRepository.deleteById(borrowedBookRepository.findBorrowedBookByBookOwnerId(bookOwnerId).get().getBookOwnerId());
        }
        if(!waitingListRepository.findWaitingListByBookId(id).isEmpty()){
            waitingListRepository.deleteAllByBookId(id);
        }
    }

    public void deleteAllBooks()throws EmptyDatabaseException{
        validateNotEmptyDatabase();
        borrowedBookRepository.deleteAll();
        bookOwnerRepository.deleteAll();
        waitingListRepository.deleteAll();
        bookRepository.deleteAll();
    }

    public List<Object> getAvailableBooks() throws EmptyDatabaseException{
        validateNotEmptyDatabase();
        return bookRepository.getAvailableBooks();
    }

    public List<Object> getBookByTitleOrAuthor(String  words) throws EmptyDatabaseException{
        validateNotEmptyDatabase();
        return bookRepository.getBookByTitleOrAuthor(words);
    }
}
