package com.endava.bookrental.repositories;


import com.endava.bookrental.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("select b from books b where b.title=:title and b.author=:author and b.isbn=:isbn")
    public Optional<Book> findByTitleAuthorISBN(String title, String author, BigInteger isbn);
}
