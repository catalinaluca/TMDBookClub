package com.endava.bookrental.repositories;

import com.endava.bookrental.models.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook,Integer> {
    @Query("select b from borrowed_books b where b.userId=:id")
    public BorrowedBook getBorrowedBooksForUserId(Integer id);
}
