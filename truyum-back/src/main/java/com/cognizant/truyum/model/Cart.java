package com.cognizant.truyum.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<MenuItem> menuItemList = new ArrayList<>();
    private double total;

    public void addToCard(MenuItem menuItem) {
        menuItemList.add(menuItem);
        total++;
    }

    public void removeFromCard(MenuItem menuItem) {
        menuItemList.remove(menuItem);
        total--;
    }

    public void removeFromCardById(long menuItemId) {
        menuItemList.stream()
                .filter(item -> item.getId() == menuItemId)
                .findFirst()
                .ifPresent(this::removeFromCard);
        total--;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "menuItemList=" + menuItemList +
                ", total=" + total +
                '}';
    }
}
