package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.MenuItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MenuItemDaoCollectionImpl implements MenuItemDao{
    private List<MenuItem> menuItemList;
    public MenuItemDaoCollectionImpl() {
        if (menuItemList != null) return;
        menuItemList = new ArrayList<>();

        // init list with default menu items
        // First row
        menuItemList.add(new MenuItem(
                1,
                "Sandwich",
                99.00f,
                true,
                "15/03/2017",
                "Main course",
                true
        ));

        // Second row
        menuItemList.add(new MenuItem(
                2,
                "Burger",
                129.00f,
                true,
                "23/11/2017",
                "Main course",
                false
        ));

        // Third row
        menuItemList.add(new MenuItem(
                3,
                "Pizza",
                149.00f,
                true,
                "21/08/2018",
                "Main course",
                false
        ));

        // Fourth row
        menuItemList.add(new MenuItem(
                4,
                "French Fries",
                57.00f,
                false,
                "02/07/2017",
                "Starters",
                true
        ));

        // Fifth row
        menuItemList.add(new MenuItem(
                5,
                "Chocolate Brownie",
                32.00f,
                true,
                "02/11/2022",
                "Dessert",
                true
        ));
    }

    @Override
    public List<MenuItem> getMenuItemListAdmin() {
        return menuItemList;
    }

    @Override
    public List<MenuItem> getMenuItemListCustomer() {
        return menuItemList.stream()
                .filter( menuItem -> menuItem.isActive() && menuItem.getDateOfLaunch().compareTo(new Date()) < 0)
                .map( menuItem -> new MenuItem(
                    menuItem.getId(),
                    menuItem.getName(),
                    menuItem.isFreeDelivery(),
                    menuItem.getPrice(),
                    menuItem.getCategory()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void modifyMenuItem(MenuItem menuItem){
        menuItemList = menuItemList.stream()
                .map(item -> item.getId() == menuItem.getId() ? menuItem : item)
                .collect(Collectors.toList());
    }

    @Override
    public MenuItem getMenuItem(long menuItemId) throws EntityNotFoundException {
        return menuItemList.stream()
                .filter(item -> item.getId() == menuItemId)
                .findFirst().orElseThrow(() -> new EntityNotFoundException(MenuItem.class, menuItemId));
    }
}
