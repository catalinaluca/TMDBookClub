package com.endava.bookrental.services;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getBook(Integer id) {
        return bookRepository.getReferenceById(id);
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
