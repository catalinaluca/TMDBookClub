package com.endava.bookrental.repositories;

import com.endava.bookrental.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishList,Integer> {

    public List<WishList> findAllByUserId(Integer userId);

    @Transactional
    public void deleteByBookIdAndUserId(Integer userId,Integer bookId);
}
