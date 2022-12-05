package org.cognizant.truyum.dao;

import org.cognizant.truyum.model.MenuItem;

import java.util.List;

public interface CartDao {
    void addCartItem(long userId, long menuItemId);
    List<MenuItem> getAllCartItems() throws CartEmptyException;
}
