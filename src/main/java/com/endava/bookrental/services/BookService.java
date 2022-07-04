package com.endava.bookrental.services;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Integer id){
        bookRepository.deleteById(id);
    }

    public void deleteAllBooks(){
        bookRepository.deleteAll();
    }
}
