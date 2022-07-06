package com.endava.bookrental.services;

import com.endava.bookrental.controllers.BookOwnerController;
import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.BorrowedBook;
import com.endava.bookrental.repositories.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class BorrowedBookService {
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Autowired
    private BookOwnerService bookOwnerService;

    public List<BorrowedBook>getAllBorrowedBooks(){
        return borrowedBookRepository.findAll();
    }

    public BorrowedBook getBorrowedBooksForUserId(Integer id){
        return borrowedBookRepository.getBorrowedBooksForUserId(id);
    }

    public BorrowedBook borrowBook(Integer userId,Integer bookId,Integer period){
        BorrowedBook borrowedBook=new BorrowedBook();
        borrowedBook.setUserId(userId);
        borrowedBook.setBookOwnerId(bookOwnerService.getBookOwnerIdByBookId(bookId).get());
        borrowedBook.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
        borrowedBook.setEndDate(Timestamp.valueOf(LocalDateTime.now().plusDays(period)));
        return borrowedBookRepository.save(borrowedBook);
    }

    public BorrowedBook extendPeriod(Integer rentingId){
        BorrowedBook borrowedBook=borrowedBookRepository.findById(rentingId).get();
        borrowedBookRepository.deleteById(rentingId);
        borrowedBook.setEndDate(Timestamp.valueOf(borrowedBook.getEndDate().toLocalDateTime().plusDays(14)));
        return borrowedBookRepository.save(borrowedBook);
    }

    public List<String> getBooksOwned(Integer ownerId){
        return borrowedBookRepository.getOwnedBooks(ownerId);
    }
}
