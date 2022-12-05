package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

import java.util.HashMap;
import java.util.List;

public class CartDaoCollectionImpl implements CartDao{
    private HashMap<Long, Cart> userCarts;

    public CartDaoCollectionImpl() {
        if (userCarts != null) return;
        userCarts = new HashMap<>();
        userCarts.put(1l, new Cart());
        userCarts.put(2l, new Cart());
    }

    @Override
    public void addCartItem(long userId, long menuItemId) {
        MenuItemDaoCollectionImpl menuItemDao = new MenuItemDaoCollectionImpl();
        try {
            Cart cart = userCarts.containsKey(userId) ? userCarts.get(userId) : new Cart();
            cart.addToCard(menuItemDao.getMenuItem(menuItemId));
            userCarts.put(userId, cart);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
        List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
        if (userCarts.isEmpty()) {
            throw new CartEmptyException(String.format("The user number %d's card is empty", userId));
        }
        return menuItemList;
    }

    @Override
    public void removeCartItem(long userId, long menuItemId) {
        if (!userCarts.containsKey(userId)) return;
        userCarts.get(userId).removeFromCardById(menuItemId);
    }
}
