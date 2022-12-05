package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDaoSqlImpl implements MenuItemDao{

    @Override
    public List<MenuItem> getMenuItemListAdmin() {
        List<MenuItem> menuItemList = new ArrayList<>();
        final String QUERY =
                "SELECT" +
                "       mi.id," +
                "       mi.name," +
                "       mi.price," +
                "       mi.active," +
                "       mi.date_of_launch," +
                "       c.name AS category," +
                "       mi.free_delivery " +
                "FROM menu_items mi " +
                "LEFT JOIN categories c ON mi.category_id = c.id";

        try(Connection conn = ConnectionHandler.getConnection();
            PreparedStatement ps = conn.prepareStatement(QUERY);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                menuItemList.add(new MenuItem(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getBoolean("active"),
                        rs.getDate("date_of_launch"),
                        rs.getString("category"),
                        rs.getBoolean("free_delivery")
                ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return menuItemList;
    }

    @Override
    public List<MenuItem> getMenuItemListCustomer() {
        List<MenuItem> menuItemList = new ArrayList<>();
        final String QUERY = "SELECT " +
                            "       mi.id, " +
                            "       mi.name, " +
                            "       mi.free_delivery," +
                            "       mi.price, " +
                            "       c.name as category " +
                            "FROM menu_items mi " +
                            "LEFT JOIN categories c on mi.category_id = c.id " +
                            "WHERE mi.active = 1 AND current_date > mi.date_of_launch";

        try(Connection conn = ConnectionHandler.getConnection();
            PreparedStatement ps = conn.prepareStatement(QUERY);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                menuItemList.add(new MenuItem(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBoolean("free_delivery"),
                        rs.getFloat("price"),
                        rs.getString("category")
                ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return menuItemList;
    }

    @Override
    public void modifyMenuItem(MenuItem menuItem) {
        final String QUERY = "UPDATE menu_items " +
                             "SET name = ?, " +
                             "    price = ?, " +
                             "    active = ?, " +
                             "    date_of_launch = ?, " +
                             "    category_id = (SELECT id FROM categories WHERE name = ?), " +
                             "    free_delivery = ? " +
                             "WHERE id = ?;";

        try(Connection conn = ConnectionHandler.getConnection();
            PreparedStatement ps = conn.prepareStatement(QUERY);
        ) {
            ps.setString(1, menuItem.getName());
            ps.setFloat(2, menuItem.getPrice());
            ps.setBoolean(3, menuItem.isActive());
            ps.setObject(4, menuItem.getDateOfLaunch() != null ? new java.sql.Date(menuItem.getDateOfLaunch().getTime()) : null);
            ps.setString(5, menuItem.getCategory());
            ps.setBoolean(6, menuItem.isFreeDelivery());
            ps.setLong(7, menuItem.getId());
            ps.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MenuItem getMenuItem(long menuItemId) throws EntityNotFoundException {
        final String QUERY = "SELECT " +
                            "       mi.id," +
                            "       mi.name, " +
                            "       mi.price, " +
                            "       mi.active, " +
                            "       mi.date_of_launch, " +
                            "       c.name as category, " +
                            "       mi.free_delivery " +
                            "FROM menu_items mi " +
                            "LEFT JOIN categories c on mi.category_id = c.id " +
                            "WHERE mi.id = ?;";

        try(Connection conn = ConnectionHandler.getConnection();
            PreparedStatement ps = conn.prepareStatement(QUERY);
        ) {
            ps.setLong(1, menuItemId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new EntityNotFoundException(MenuItem.class, menuItemId);
            return new MenuItem(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getFloat("price"),
                    rs.getBoolean("active"),
                    rs.getDate("date_of_launch"),
                    rs.getString("category"),
                    rs.getBoolean("free_delivery")
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}