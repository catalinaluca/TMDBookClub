package com.endava.bookrental.repositories;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BookOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Repository
public interface BookOwnerRepository extends JpaRepository<BookOwner,Integer> {
    @Query("SELECT b FROM book_owner b where b.ownerId=:id")
    public Optional<BookOwner> getBooksForOwner(Integer id);

    @Query("SELECT b FROM book_owner b where b.bookId=:id")
    public Optional<BookOwner> getOwnerForBook(Integer id);

    public Optional<Integer> getBookOwnerByBookId(Integer bookId);

    @Query("select bo.bookOwnerId from book_owner bo where bo.bookId=:bookId")
    public Optional<Integer> getBookOwnerIdByBookId(Integer bookId);
}
