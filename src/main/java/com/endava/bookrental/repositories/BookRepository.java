package com.endava.bookrental.repositories;


import com.endava.bookrental.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("select b from books b where b.title=:title and b.author=:author and b.isbn=:isbn")

    public Optional<Book> findByTitleAuthorISBN(String title, String author, BigInteger isbn);

    @Query("select b from books b inner join book_owner bo on bo.bookId=b.bookId where bo.bookOwnerId not in (select bb.bookOwnerId from borrowed_books bb)")
    public List<Book> getAvailableBooks();

    @Query(value = "Select book_filtered.isbn,book_filtered.title,book_filtered.author,bb.end_date from " +
            "(SELECT b.book_id,b.title,b.author,b.isbn FROM books b where title like %?1% or author like %?2%) as book_filtered "+
                                "left join book_owner bo on book_filtered.book_id=bo.book_id "+
                                "left join borrowed_books bb on bo.book_owner_id=bb.book_owner_id",nativeQuery = true)
    public List<Object> getBookByTitleOrAuthor(String title, String author);
}
