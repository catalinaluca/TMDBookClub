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

    public Book addBook(Book book){
         return  bookRepository.findByTitleAuthorISBN(book.getTitle(),book.getAuthor(),book.getIsbn()).isPresent()?
                    bookRepository.findByTitleAuthorISBN(book.getTitle(),book.getAuthor(),book.getIsbn()).get():
                        bookRepository.save(book);
    }

    public void deleteBook(Integer id) throws BookNotFoundException, BookOwnerRelationNotFoundException {
        validateBook(bookRepository.findById(id));
        if(bookOwnerRepository.getBookOwnerIdByBookId(id).isPresent()) {
            bookOwnerRepository.deleteById(bookOwnerRepository.getBookOwnerIdByBookId(id).get());
        }else throw new BookOwnerRelationNotFoundException();
        Integer bookOwnerId=bookOwnerRepository.getBookOwnerIdByBookId(id).get();
        if(borrowedBookRepository.findBorrowedBookByBookOwnerId(bookOwnerId).isPresent()){
            borrowedBookRepository.deleteById(borrowedBookRepository.findBorrowedBookByBookOwnerId(bookOwnerId).get().getBookOwnerId());
        }
        if(waitingListRepository.findWaitingListByBookId(id).isPresent()){
            waitingListRepository.deleteById(waitingListRepository.findWaitingListByBookId(id).get().getWaitingId());
        }
        bookRepository.deleteById(id);
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

    public List<Object> getBookByTitleOrAuthor(String  title, String  author) throws EmptyDatabaseException{
        validateNotEmptyDatabase();
        return bookRepository.getBookByTitleOrAuthor(title,author);
    }
}
