package com.endava.bookrental.repositories;

import com.endava.bookrental.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<WishList,Integer> {

    public List<WishList> findAllByUserId(Integer userId);

    public Optional<WishList> findWishListByBookId(Integer bookId);

    @Transactional
    public void deleteWishListByUserIdAndBookId(Integer userId,Integer bookId);
}
