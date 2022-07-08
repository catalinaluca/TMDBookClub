package com.endava.bookrental.models;

import javax.persistence.*;

@Entity(name = "book_owner")
public class BookOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookOwnerId;
    @Column(nullable = false)
    private Integer bookId;
    @Column(nullable = false)
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
