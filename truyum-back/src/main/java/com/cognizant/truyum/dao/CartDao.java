package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.MenuItem;

import java.util.List;

public interface CartDao {
    void addCartItem(long userId, long menuItemId);
    List<MenuItem> getAllCartItems(long userId) throws CartEmptyException;
    void removeCartItem(long userId, long menuItemId);
}
