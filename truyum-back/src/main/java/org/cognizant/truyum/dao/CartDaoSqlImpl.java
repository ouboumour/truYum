package org.cognizant.truyum.dao;

import org.cognizant.truyum.model.MenuItem;

import java.util.List;

public class CartDaoSqlImpl implements CartDao{
    @Override
    public void addCartItem(long userId, long menuItemId) {

    }

    @Override
    public List<MenuItem> getAllCartItems() throws CartEmptyException {
        return null;
    }
}
