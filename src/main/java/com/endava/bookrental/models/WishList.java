package com.endava.bookrental.models;

import javax.persistence.*;
@Entity(name="wishlist")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wish;

    @Column(nullable = false)
    private Integer bookId;

    @Column(nullable = false)
    private Integer userId;

    public Integer getWish() {
        return wish;
    }

    public void setWish(Integer wish) {
        this.wish = wish;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "wish=" + wish +
                ", bookId=" + bookId +
                ", userId=" + userId +
                '}';
    }
}
