package com.cognizant.truyum.model;

import com.cognizant.truyum.util.DateUtil;

import java.text.ParseException;
import java.util.Date;

public class MenuItem {
    private long id;
    private String name;
    private float price;

    private boolean active;
    private Date dateOfLaunch;
    private String category;
    private boolean freeDelivery;

    public MenuItem() {}

    /**
     * The constructor bellow is designed specifically for admin menu items
     * since admin should have access to all the fields values
     * @param id
     * @param name
     * @param price
     * @param active
     * @param dateOfLaunch
     * @param category
     * @param freeDelivery
     */
    public MenuItem(
            long id,
            String name,
            float price,
            boolean active,
            Date dateOfLaunch,
            String category,
            boolean freeDelivery
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.active = active;
        this.dateOfLaunch = dateOfLaunch;
        this.category = category;
        this.freeDelivery = freeDelivery;
    }

    /**
     * The constructor bellow is designed specifically for admin menu items
     * since admin should have access to all the fields values
     * @param id
     * @param name
     * @param price
     * @param active
     * @param stringDateOfLaunch should match the "dd/MM/yyyy" format
     * @param category
     * @param freeDelivery
     */
    public MenuItem(
            long id,
            String name,
            float price,
            boolean active,
            String stringDateOfLaunch,
            String category,
            boolean freeDelivery
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.active = active;
        this.category = category;
        this.freeDelivery = freeDelivery;
        try {
            this.dateOfLaunch = DateUtil.convertToDate(stringDateOfLaunch);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * The constructor bellow is designed specifically for customer menu items
     * to hide admin sensitive fields values
     * @param id
     * @param name
     * @param freeDelivery
     * @param price
     * @param category
     */
    public MenuItem(
            long id,
            String name,
            boolean freeDelivery,
            float price,
            String category
    ) {
        this.id = id;
        this.name = name;
        this.freeDelivery = freeDelivery;
        this.price = price;
        this.category = category;
        this.active = true;
    }

    public MenuItem(
            long id,
            String name,
            boolean freeDelivery,
            float price
    ) {
        this.id = id;
        this.name = name;
        this.freeDelivery = freeDelivery;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDateOfLaunch() {
        return dateOfLaunch;
    }

    public void setDateOfLaunch(Date dateOfLaunch) {
        this.dateOfLaunch = dateOfLaunch;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isFreeDelivery() {
        return freeDelivery;
    }

    public void setFreeDelivery(boolean freeDelivery) {
        this.freeDelivery = freeDelivery;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", active=" + active +
                ", dateOfLaunch=" + dateOfLaunch +
                ", category='" + category + '\'' +
                ", freeDelivery=" + freeDelivery +
                '}';
    }
}
