package com.endava.bookrental.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "book_owner")
public class BookOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookOwnerId;
    private Integer bookId;
    private Integer ownerId;

    public Integer getBookOwnerId() {
        return bookOwnerId;
    }

    public void setBookOwnerId(Integer bookOwnerId) {
        this.bookOwnerId = bookOwnerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "BookOwner{" +
                "bookOwnerId=" + bookOwnerId +
                ", bookId=" + bookId +
                ", ownerId=" + ownerId +
                '}';
    }
}
