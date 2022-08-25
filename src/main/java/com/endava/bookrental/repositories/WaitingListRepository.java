package com.endava.bookrental.repositories;

import com.endava.bookrental.models.Book;
import com.endava.bookrental.models.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface WaitingListRepository extends JpaRepository<WaitingList,Integer> {
    @Transactional
    public void deleteByWaiterId(Integer id);
    public List<WaitingList> findWaitingListByBookId(Integer id);

    public Optional<WaitingList> findWaitingListByWaiterId(Integer userId);

    @Transactional
    public void deleteAllByBookId(Integer id);
}
