package com.endava.bookrental.services;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BookOwner;
import com.endava.bookrental.models.User;
import com.endava.bookrental.repositories.BookOwnerRepository;
import com.endava.bookrental.repositories.BookRepository;
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

    @Autowired
    private UserService userService;
    public List<BookOwner> getAllBooksOwners(){
        return bookOwnerRepository.findAll();
    }

    public Optional<BookOwner> getBooksForOwner(Integer id){
        return bookOwnerRepository.getBooksForOwner(id);
    }

    public Optional<BookOwner> getOwnerForBook(Integer id){
        return bookOwnerRepository.getOwnerForBook(id);
    }

    public BookOwner addBookForOwner(Book book, Integer id){
        bookService.addBook(book);
        BookOwner bookOwner=new BookOwner();
        bookOwner.setBookId(book.getBookId());
        bookOwner.setOwnerId(id);
        return bookOwnerRepository.save(bookOwner);
    }

    public void deleteAll(){
        bookOwnerRepository.deleteAll();
    }

    public Optional<Book> getBookForBookOwnerId(Integer id){
        return bookService.getBookById(bookOwnerRepository.getBookForBookOwnerId(id).get());
    }

    public void deleteBookOwner(Integer id){
        bookService.deleteBook(getBookForBookOwnerId(id).get().getBookId());
        bookOwnerRepository.deleteById(id);
    }
}

