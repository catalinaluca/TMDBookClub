package com.endava.bookrental.repositories;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BookOwner;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookOwnerRepository extends JpaRepository<BookOwner, Integer> {
    @Query("SELECT b FROM book_owner bo INNER JOIN books b on b.bookId=bo.bookId where bo.ownerId=:id")
    public List<Object> getBooksForOwner(Integer id);

    @Query("SELECT b FROM book_owner b where b.bookId=:id")
    public Optional<BookOwner> getOwnerForBook(Integer id);

    public Optional<BookOwner> getBookOwnerByBookId(Integer bookId);


    public List<Optional<BookOwner>> getBookOwnerByOwnerId(Integer ownerId);

    @Query("SELECT b from books b inner join book_owner bo on b.bookId=bo.bookId where bo.bookOwnerId=:bOId")
    public Optional<Book> getBookByBookOwnerId(Integer bOId);

    public void deleteBookOwnerByOwnerId(Integer userId);

 }
