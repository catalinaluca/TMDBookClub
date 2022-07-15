package com.endava.bookrental.prototypes;

import com.endava.bookrental.models.WaitingList;

public class WaitingListPrototype {

    public static WaitingList getWaitingListPrototype(){
        WaitingList waitingListPrototype=new WaitingList();
        waitingListPrototype.setWaitingId(1);
        waitingListPrototype.setBookId(1);
        waitingListPrototype.setWaiterId(1);
        return waitingListPrototype;
    }
}
