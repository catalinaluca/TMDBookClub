package com.endava.bookrental.repositories;


import com.endava.bookrental.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
