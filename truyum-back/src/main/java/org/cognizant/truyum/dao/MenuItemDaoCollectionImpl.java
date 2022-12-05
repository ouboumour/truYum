package org.cognizant.truyum.dao;

import org.cognizant.truyum.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemDaoCollectionImpl implements MenuItemDao{

    List<MenuItem> menuItemList;
    public MenuItemDaoCollectionImpl() {
        if (menuItemList == null) {
            menuItemList = new ArrayList<>();
            // TODO: add data to the collection from db
        }
    }

    @Override
    public List<MenuItem> getMenuItemListAdmin() {
        return null;
    }

    @Override
    public List<MenuItem> getMenuItemListCustomer() {
        return null;
    }

    @Override
    public void modifyMenuItem(MenuItem menuItem) {

    }

    @Override
    public MenuItem getMenuItem(long menuItemId) {
        return null;
    }
}
