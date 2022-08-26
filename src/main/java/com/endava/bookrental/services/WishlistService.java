package com.endava.bookrental.services;

import com.endava.bookrental.exceptions.BookNotFoundException;
import com.endava.bookrental.exceptions.BorrowedBookNotFoundException;
import com.endava.bookrental.exceptions.EmptyDatabaseException;
import com.endava.bookrental.exceptions.UserNotFoundException;
import com.endava.bookrental.models.WishList;
import com.endava.bookrental.repositories.BookRepository;
import com.endava.bookrental.repositories.UserRepository;
import com.endava.bookrental.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    private void validateNotEmptyDatabase() throws EmptyDatabaseException {
        if(wishlistRepository.findAll().isEmpty())throw new EmptyDatabaseException();
    }

    private void validateUser(Integer userId) throws UserNotFoundException {
        if(userRepository.findById(Long.valueOf(userId)).isEmpty())throw new UserNotFoundException();
    }
    private void validateBook(Integer bookId) throws BookNotFoundException {
        if(bookRepository.findById(bookId).isEmpty())throw new BookNotFoundException();
    }

    public List<WishList> getAll() throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        return wishlistRepository.findAll();
    }

    public List<WishList> getWishlistForUser(Integer userId) throws UserNotFoundException, EmptyDatabaseException {
        validateUser(userId);
        validateNotEmptyDatabase();
        return wishlistRepository.findAllByUserId(userId);
    }

    public WishList addBookOnWishlist(Integer userId, Integer bookId) throws UserNotFoundException, BookNotFoundException {
        validateUser(userId);
        validateBook(bookId);
        WishList wishList=new WishList();
        wishList.setUserId(userId);
        wishList.setBookId(bookId);
        return wishlistRepository.save(wishList);
    }

    public void deleteBookFromWishlist(Integer userId,Integer bookId) throws BookNotFoundException, UserNotFoundException {
        validateUser(userId);
        validateBook(bookId);
        wishlistRepository.deleteByBookIdAndUserId(userId,bookId);
    }

    public void deleteAllWishes() throws EmptyDatabaseException {
        validateNotEmptyDatabase();
        wishlistRepository.deleteAll();
    }
}
