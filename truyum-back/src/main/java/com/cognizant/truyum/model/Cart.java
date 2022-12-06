package com.cognizant.truyum.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<MenuItem> menuItemList = new ArrayList<>();
    private double total;

    public void addToCard(MenuItem menuItem) {
        total += menuItem.getPrice();
        menuItemList.add(menuItem);
        System.out.println(menuItem.getPrice());
        System.out.println(total);
    }

    public void removeFromCard(MenuItem menuItem) {
        if (menuItemList.remove(menuItem)) {
            total -= menuItem.getPrice();
        }
        System.out.println(menuItem.getPrice());
        System.out.println(total);
    }

    public void removeFromCardById(long menuItemId) {
        menuItemList.stream()
                .filter(item -> item.getId() == menuItemId)
                .findFirst()
                .ifPresent(this::removeFromCard);
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
