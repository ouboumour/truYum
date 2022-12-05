package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.MenuItem;

import java.util.List;

public interface MenuItemDao {
    List<MenuItem> getMenuItemListAdmin();
    List<MenuItem> getMenuItemListCustomer();
    void modifyMenuItem(MenuItem menuItem);
    MenuItem getMenuItem(long menuItemId) throws EntityNotFoundException;
}
