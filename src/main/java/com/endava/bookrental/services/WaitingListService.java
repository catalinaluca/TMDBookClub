package com.endava.bookrental.services;

import com.endava.bookrental.models.WaitingList;
import com.endava.bookrental.repositories.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaitingListService {
    @Autowired
    private WaitingListRepository waitingListRepository;

    public Object addWaiter(Integer userId,Integer bookId){
        WaitingList waitingList=new WaitingList();
        if(waitingListRepository.getRentedBooks().contains(bookId)){
            waitingList.setWaiterId(userId);
            waitingList.setBookId(bookId);
        }
        return waitingListRepository.save(waitingList);
    }
}
