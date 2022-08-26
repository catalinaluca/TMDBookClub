package com.endava.bookrental.prototypes;

import com.endava.bookrental.models.WishList;

public class WishlistPrototype {

    public static WishList getWishlistPrototype(){
        WishList wishList=new WishList();
        wishList.setUserId(1);
        wishList.setBookId(1);
        wishList.setWish(1);
        return wishList;
    }
}
