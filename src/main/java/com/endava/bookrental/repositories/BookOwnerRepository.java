package com.endava.bookrental.repositories;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BookOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface BookOwnerRepository extends JpaRepository<BookOwner,Integer> {
    @Query("SELECT b FROM book_owner b where b.ownerId=:id")
    public Optional<BookOwner> getBooksForOwner(Integer id);

    @Query("SELECT b FROM book_owner b where b.bookId=:id")
    public Optional<BookOwner> getOwnerForBook(Integer id);

    @Query("select b.bookId from book_owner b where b.bookOwnerId=:id")
    public Optional<Integer> getBookForBookOwnerId(Integer id);

}
