package com.endava.bookrental.services;

import com.endava.bookrental.exceptions.*;
import com.endava.bookrental.models.WaitingList;
import com.endava.bookrental.repositories.BookRepository;
import com.endava.bookrental.repositories.BorrowedBookRepository;
import com.endava.bookrental.repositories.UserRepository;
import com.endava.bookrental.repositories.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(userRepository.findById(Long.valueOf(userId)).isEmpty())throw new UserNotFoundException();
    }

    private void validateWaiterId(Integer waiterId) throws WaiterNotFoundException {
        if(waitingListRepository.findById(waiterId).isEmpty())throw new WaiterNotFoundException();
    }
    private void validateBook(Integer bookId) throws BorrowedBookNotFoundException, BookNotFoundException {
        if(bookRepository.findById(bookId).isEmpty())throw new BookNotFoundException();
        if(borrowedBookRepository.getBookOwnerIdForBookId(bookId).isEmpty())throw new BorrowedBookNotFoundException();
    }

    private void validateNotEmptyDatabase() throws EmptyDatabaseException{
        if(waitingListRepository.findAll().isEmpty())throw new EmptyDatabaseException();
    }

    public Object addWaiter(Integer userId,Integer bookId) throws UserNotFoundException, BorrowedBookNotFoundException, BookNotFoundException {
        validateUser(userId);
        validateBook(bookId);
        WaitingList waitingList=new WaitingList();
        waitingList.setWaiterId(userId);
        waitingList.setBookId(bookId);
        return waitingListRepository.save(waitingList);
    }

    public List<WaitingList> getAllWaiters() throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        return waitingListRepository.findAll();
    }

    public void deleteAllWaiters() throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        waitingListRepository.deleteAll();
    }

    public void deleteWaiterWithId(Integer id) throws WaiterNotFoundException{
        validateWaiterId(id);
        waitingListRepository.deleteById(id);
    }
}
