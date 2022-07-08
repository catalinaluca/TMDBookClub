package com.endava.bookrental.repositories;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook,Integer> {
    @Query("select b from borrowed_books b where b.userId=:id")
    public List<BorrowedBook> getBorrowedBooksForUserId(Integer id);
    @Query("Select u.username,u.name,u.surname,b.title,b.author,bb.userId,bb.endDate FROM book_owner bo left join books b on bo.bookId=b.bookId inner join users u on bo.ownerId=u.userId left join borrowed_books bb on bb.bookOwnerId=bo.bookOwnerId  where bo.ownerId=:ownerId")
    public List<Object> getOwnedBooks(Integer ownerId);

    @Query("SELECT b.isbn,b.title,b.author,bb.endDate FROM borrowed_books bb inner join book_owner bo on bb.bookOwnerId=bo.bookOwnerId left join books b on bo.bookId=b.bookId where bb.userId=:userId")
    public List<Object> getRentedBooks(Integer userId);
}
