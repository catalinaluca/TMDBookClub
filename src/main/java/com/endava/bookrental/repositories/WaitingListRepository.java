package com.endava.bookrental.repositories;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaitingListRepository extends JpaRepository<WaitingList,Integer> {
    @Query("select b.bookId from books b inner join book_owner bo on bo.bookId=b.bookId where bo.bookOwnerId in (select bb.bookOwnerId from borrowed_books bb)")
    public List<Integer> getRentedBooks();
}
