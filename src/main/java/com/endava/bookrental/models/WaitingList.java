package com.endava.bookrental.models;

import javax.persistence.*;

@Entity(name = "waiting_list")
public class WaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer waitingId;
    @Column(nullable = false)
    private Integer bookId;
    @Column(nullable = false)
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
