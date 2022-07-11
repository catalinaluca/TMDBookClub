package com.endava.bookrental.services;

import com.endava.bookrental.exceptions.BookNotFoundException;
import com.endava.bookrental.exceptions.BorrowedBookNotFoundException;
import com.endava.bookrental.exceptions.UserNotFoundException;
import com.endava.bookrental.models.WaitingList;
import com.endava.bookrental.repositories.BookRepository;
import com.endava.bookrental.repositories.BorrowedBookRepository;
import com.endava.bookrental.repositories.UserRepository;
import com.endava.bookrental.repositories.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaitingListService {
    @Autowired
    private WaitingListRepository waitingListRepository;

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;
    private void validateUser(Integer userId) throws UserNotFoundException {
        if(!userRepository.findAll().contains(Long.valueOf(userId)))throw new UserNotFoundException();
    }

    private void validateBook(Integer bookId) throws BorrowedBookNotFoundException, BookNotFoundException {
        if(!bookRepository.findAll().contains(bookId))throw new BookNotFoundException();
        if(!borrowedBookRepository.findAll().contains(bookId))throw new BorrowedBookNotFoundException();
    }
    public Object addWaiter(Integer userId,Integer bookId) throws UserNotFoundException, BorrowedBookNotFoundException, BookNotFoundException {
        validateUser(userId);
        validateBook(bookId);
        WaitingList waitingList=new WaitingList();
        waitingList.setWaiterId(userId);
        waitingList.setBookId(bookId);
        return waitingListRepository.save(waitingList);
    }
}
