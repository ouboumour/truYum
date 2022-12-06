package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartDaoCollectionImplTest {
    @Test
    public void testAddCartItem() throws Exception{
        CartDaoCollectionImpl cartDaoCollection = new CartDaoCollectionImpl();
        Assertions.assertEquals(0, cartDaoCollection.getAllCartItems(1).size());

        cartDaoCollection.addCartItem(1, 3);

        Assertions.assertEquals(1, cartDaoCollection.getAllCartItems(1).size());

        MenuItem addedItem = cartDaoCollection.getAllCartItems(1).get(0);

        // check the added
        Assertions.assertEquals(3, addedItem.getId());
        Assertions.assertEquals("Pizza", addedItem.getName());
        Assertions.assertEquals(149.00f, addedItem.getPrice());
        Assertions.assertTrue(addedItem.isActive());
        Assertions.assertEquals(DateUtil.convertToDate("21/08/2018"), addedItem.getDateOfLaunch());
        Assertions.assertEquals("Main course", addedItem.getCategory());
        Assertions.assertFalse(addedItem.isFreeDelivery());
    }

    @Test
    public void testGetAllCartItems() throws Exception {
        CartDaoCollectionImpl cartDaoCollection = new CartDaoCollectionImpl();
        cartDaoCollection.addCartItem(1, 3);

        Assertions.assertEquals(1, cartDaoCollection.getAllCartItems(1l).size());
        Assertions.assertEquals(0, cartDaoCollection.getAllCartItems(2l).size());
    }

    @Test
    public void testRemoveCartItem() throws Exception {
        CartDaoCollectionImpl cartDaoCollection = new CartDaoCollectionImpl();
        cartDaoCollection.addCartItem(1, 3);

        Assertions.assertEquals(1, cartDaoCollection.getAllCartItems(1l).size());

        cartDaoCollection.removeCartItem(1, 3);
        Assertions.assertEquals(0, cartDaoCollection.getAllCartItems(1l).size());
    }
}
