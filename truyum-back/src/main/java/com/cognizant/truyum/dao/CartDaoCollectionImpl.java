package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.MenuItem;

import java.util.List;

public class CartDaoCollectionImpl implements CartDao{

    @Override
    public void addCartItem(long userId, long menuItemId) {

    }

    @Override
    public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
        return null;
    }

    @Override
    public void removeCartItem(long userId, long menuItemId) {

    }
}
