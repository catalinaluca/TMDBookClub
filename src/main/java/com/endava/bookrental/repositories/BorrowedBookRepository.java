package com.endava.bookrental.repositories;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;


@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook,Integer> {
    public Optional<BorrowedBook> findBorrowedBookByBookOwnerId(Integer bookOwnerId);

    @Query("Select bb from borrowed_books bb inner join book_owner bo on bb.bookOwnerId=bo.bookOwnerId where bo.bookId=:bookId")
    public Optional<BorrowedBook> findBorrowedBookByBookId(Integer bookId);
    @Query("Select b,bb FROM book_owner bo left join books b on bo.bookId=b.bookId inner join users u on bo.ownerId=u.userId left join borrowed_books bb on bb.bookOwnerId=bo.bookOwnerId  where bo.ownerId=:ownerId")
    public List<Object> getOwnedBooks(Integer ownerId);

    @Query("SELECT b,bb FROM borrowed_books bb inner join book_owner bo on bb.bookOwnerId=bo.bookOwnerId left join books b on bo.bookId=b.bookId where bb.userId=:userId")
    public List<Object> getRentedBooks(Integer userId);

    @Query("SELECT bo.bookOwnerId FROM book_owner bo inner join borrowed_books bb on bb.bookOwnerId=bo.bookOwnerId where bo.bookId=:id")
    public Optional<Integer> getBookOwnerIdForBookId(Integer id);
}
