package com.endava.bookrental.repositories;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WaitingListRepository extends JpaRepository<WaitingList,Integer> {
    public List<WaitingList> findWaitingListByBookId(Integer id);

    public void deleteAllByBookId(Integer id);
}
