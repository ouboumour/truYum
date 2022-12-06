package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

public class MenuItemDaoCollectionImplTest {

    @Test
    public void testGetMenuItemListAdmin() throws ParseException {
        MenuItemDaoCollectionImpl menuItemDao = new MenuItemDaoCollectionImpl();
        List<MenuItem> items = menuItemDao.getMenuItemListAdmin();
        Assertions.assertEquals(5, items.size());

        // Check first menuItem returned data
        Assertions.assertEquals(1, items.get(0).getId());
        Assertions.assertEquals("Sandwich", items.get(0).getName());
        Assertions.assertEquals(99.00f, items.get(0).getPrice());
        Assertions.assertTrue(items.get(0).isActive());
        Assertions.assertEquals(DateUtil.convertToDate("15/03/2017"), items.get(0).getDateOfLaunch());
        Assertions.assertEquals("Main course", items.get(0).getCategory());
        Assertions.assertTrue(items.get(0).isFreeDelivery());

        // Check second menuItem returned data
        Assertions.assertEquals(2, items.get(1).getId());
        Assertions.assertEquals("Burger", items.get(1).getName());
        Assertions.assertEquals(129.00f, items.get(1).getPrice());
        Assertions.assertTrue(items.get(1).isActive());
        Assertions.assertEquals(DateUtil.convertToDate("23/11/2017"), items.get(1).getDateOfLaunch());
        Assertions.assertEquals("Main course", items.get(1).getCategory());
        Assertions.assertFalse(items.get(1).isFreeDelivery());

        // Check third menuItem returned data
        Assertions.assertEquals(3, items.get(2).getId());
        Assertions.assertEquals("Pizza", items.get(2).getName());
        Assertions.assertEquals(149.00f, items.get(2).getPrice());
        Assertions.assertTrue(items.get(2).isActive());
        Assertions.assertEquals(DateUtil.convertToDate("21/08/2018"), items.get(2).getDateOfLaunch());
        Assertions.assertEquals("Main course", items.get(2).getCategory());
        Assertions.assertFalse(items.get(2).isFreeDelivery());

        // Check fourth menuItem returned data
        Assertions.assertEquals(4, items.get(3).getId());
        Assertions.assertEquals("French Fries", items.get(3).getName());
        Assertions.assertEquals(57.00f, items.get(3).getPrice());
        Assertions.assertFalse(items.get(3).isActive());
        Assertions.assertEquals(DateUtil.convertToDate("02/07/2017"), items.get(3).getDateOfLaunch());
        Assertions.assertEquals("Starters", items.get(3).getCategory());
        Assertions.assertTrue(items.get(3).isFreeDelivery());

        // Check fifth menuItem returned data
        Assertions.assertEquals(5, items.get(4).getId());
        Assertions.assertEquals("Chocolate Brownie", items.get(4).getName());
        Assertions.assertEquals(32.00f, items.get(4).getPrice());
        Assertions.assertTrue(items.get(4).isActive());
        Assertions.assertEquals(DateUtil.convertToDate("02/11/2022"), items.get(4).getDateOfLaunch());
        Assertions.assertEquals("Dessert", items.get(4).getCategory());
        Assertions.assertTrue(items.get(4).isFreeDelivery());
    }

    @Test
    public void testGetMenuItemListCustomer() {
        MenuItemDaoCollectionImpl menuItemDao = new MenuItemDaoCollectionImpl();
        List<MenuItem> items = menuItemDao.getMenuItemListCustomer();
        Assertions.assertEquals(4, items.size());

        // Check first menuItem returned data
        Assertions.assertEquals(1, items.get(0).getId());
        Assertions.assertEquals("Sandwich", items.get(0).getName());
        Assertions.assertEquals(99.00f, items.get(0).getPrice());
        Assertions.assertTrue(items.get(0).isActive());
        Assertions.assertNull(items.get(0).getDateOfLaunch());
        Assertions.assertEquals("Main course", items.get(0).getCategory());
        Assertions.assertTrue(items.get(0).isFreeDelivery());

        // Check second menuItem returned data
        Assertions.assertEquals(2, items.get(1).getId());
        Assertions.assertEquals("Burger", items.get(1).getName());
        Assertions.assertEquals(129.00f, items.get(1).getPrice());
        Assertions.assertTrue(items.get(1).isActive());
        Assertions.assertNull(items.get(1).getDateOfLaunch());
        Assertions.assertEquals("Main course", items.get(1).getCategory());
        Assertions.assertFalse(items.get(1).isFreeDelivery());

        // Check third menuItem returned data
        Assertions.assertEquals(3, items.get(2).getId());
        Assertions.assertEquals("Pizza", items.get(2).getName());
        Assertions.assertEquals(149.00f, items.get(2).getPrice());
        Assertions.assertTrue(items.get(2).isActive());
        Assertions.assertNull(items.get(2).getDateOfLaunch());
        Assertions.assertEquals("Main course", items.get(2).getCategory());
        Assertions.assertFalse(items.get(2).isFreeDelivery());

        // Check fourth menuItem returned data
        Assertions.assertEquals(5, items.get(3).getId());
        Assertions.assertEquals("Chocolate Brownie", items.get(3).getName());
        Assertions.assertEquals(32.00f, items.get(3).getPrice());
        Assertions.assertTrue(items.get(3).isActive());
        Assertions.assertNull(items.get(3).getDateOfLaunch());
        Assertions.assertEquals("Dessert", items.get(3).getCategory());
        Assertions.assertTrue(items.get(3).isFreeDelivery());
    }

    @Test
    public void testModifyMenuItem() throws Exception {
        MenuItemDaoCollectionImpl menuItemDao = new MenuItemDaoCollectionImpl();
        menuItemDao.modifyMenuItem(new MenuItem(
                1,
                "foo",
                77.77f,
                true,
                "07/09/2022",
                "bar",
                false
        ));

        MenuItem updatedItem = menuItemDao.getMenuItem(1L);
        // Check if data get updated
        Assertions.assertEquals(1, updatedItem.getId());
        Assertions.assertEquals("foo", updatedItem.getName());
        Assertions.assertEquals(77.77f, updatedItem.getPrice());
        Assertions.assertTrue(updatedItem.isActive());
        Assertions.assertEquals(DateUtil.convertToDate("07/09/2022"), updatedItem.getDateOfLaunch());
        Assertions.assertEquals("bar", updatedItem.getCategory());
        Assertions.assertFalse(updatedItem.isFreeDelivery());
    }

    @Test
    public void testGetMenuItem() throws Exception{
        MenuItemDaoCollectionImpl menuItemDao = new MenuItemDaoCollectionImpl();
        MenuItem fetchedItem = menuItemDao.getMenuItem(3L);
        // Check if correct object is fetched
        Assertions.assertEquals(3, fetchedItem.getId());
        Assertions.assertEquals("Pizza", fetchedItem.getName());
        Assertions.assertEquals(149.00f, fetchedItem.getPrice());
        Assertions.assertTrue(fetchedItem.isActive());
        Assertions.assertEquals(DateUtil.convertToDate("21/08/2018"), fetchedItem.getDateOfLaunch());
        Assertions.assertEquals("Main course", fetchedItem.getCategory());
        Assertions.assertFalse(fetchedItem.isFreeDelivery());
    }
}
