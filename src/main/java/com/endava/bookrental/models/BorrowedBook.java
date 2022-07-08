package com.endava.bookrental.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "borrowed_books")
public class BorrowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentingId;
    @Column(nullable = false)
    private Timestamp startDate;
    @Column(nullable = false)
    private Timestamp endDate;
    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private Integer bookOwnerId;

    public Integer getRentingId() {
        return rentingId;
    }

    public void setRentingId(Integer rentingId) {
        this.rentingId = rentingId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookOwnerId() {
        return bookOwnerId;
    }

    public void setBookOwnerId(Integer bookOwnerId) {
        this.bookOwnerId = bookOwnerId;
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "rentingId=" + rentingId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", userId=" + userId +
                ", bookOwnerId=" + bookOwnerId +
                '}';
    }
}
