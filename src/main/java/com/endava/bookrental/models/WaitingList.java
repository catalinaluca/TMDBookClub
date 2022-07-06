package com.endava.bookrental.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "waiting_list")
public class WaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer waitingId;

    private Integer bookId;

    private Integer waiterId;


    public Integer getWaitingId() {
        return waitingId;
    }

    public void setWaitingId(Integer waitingId) {
        this.waitingId = waitingId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Integer waiterId) {
        this.waiterId = waiterId;
    }

    @Override
    public String toString() {
        return "WaitingList{" +
                "waitingId=" + waitingId +
                ", bookId=" + bookId +
                ", waiterId=" + waiterId +
                '}';
    }
}
