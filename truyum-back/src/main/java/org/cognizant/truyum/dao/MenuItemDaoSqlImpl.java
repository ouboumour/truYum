package org.cognizant.truyum.dao;

import org.cognizant.truyum.model.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDaoSqlImpl implements MenuItemDao{
    public MenuItemDaoSqlImpl() {
    }

    @Override
    public List<MenuItem> getMenuItemListAdmin() {
        List<MenuItem> menuItemList = null;
        try {
            Connection conn = ConnectionHandler.getConnection();
            menuItemList = new ArrayList<>();
            String sqlS = "SELECT " +
                    "       mi.id," +
                    "       mi.name, " +
                    "       mi.price, " +
                    "       mi.active, " +
                    "       mi.date_of_launch, " +
                    "       c.name as category, " +
                    "       mi.free_delivery " +
                    "FROM menu_items mi " +
                    "LEFT JOIN categories c on mi.category_id = c.id;";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                menuItemList.add(new MenuItem(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getBoolean("active"),
                        resultSet.getDate("date_of_launch"),
                        resultSet.getString("category"),
                        resultSet.getBoolean("free_delivery")
                        ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return menuItemList;
    }

    @Override
    public List<MenuItem> getMenuItemListCustomer() {
        List<MenuItem> menuItemList = null;
        try {
            Connection conn = ConnectionHandler.getConnection();
            menuItemList = new ArrayList<>();
            String sqlS = "SELECT " +
                          "       mi.id, " +
                          "       mi.name, " +
                          "       mi.free_delivery," +
                          "       mi.price, " +
                          "       c.name as category " +
                          "FROM menu_items mi " +
                          "LEFT JOIN categories c on mi.category_id = c.id " +
                          "WHERE mi.active = 1 AND current_date > mi.date_of_launch;";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                menuItemList.add(new MenuItem(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("free_delivery"),
                        resultSet.getFloat("price"),
                        resultSet.getString("category")
                ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return menuItemList;
    }

    @Override
    public void modifyMenuItem(MenuItem menuItem) {
        try {
            Connection conn = ConnectionHandler.getConnection();
            String SqlS = "UPDATE menu_items " +
                          "SET name = ?, " +
                          "    price = ?, " +
                          "    active = ?, " +
                          "    date_of_launch = ?, " +
                          "    category_id = (SELECT id FROM categories WHERE name = ?), " +
                          "    free_delivery = ? " +
                          "WHERE id = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(SqlS);

            preparedStatement.setString(1, menuItem.getName());
            preparedStatement.setFloat(2, menuItem.getPrice());
            preparedStatement.setBoolean(3, menuItem.isActive());
            preparedStatement.setObject(4, new java.sql.Date(menuItem.getDateOfLaunch().getTime()));
            preparedStatement.setString(5, menuItem.getCategory());
            preparedStatement.setBoolean(6, menuItem.isFreeDelivery());
            preparedStatement.setLong(7, menuItem.getId());
            preparedStatement.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MenuItem getMenuItem(long menuItemId) {
        try {
            Connection conn = ConnectionHandler.getConnection();
            String sqlS = "SELECT " +
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

            PreparedStatement preparedStatement = conn.prepareStatement(sqlS);

            preparedStatement.setLong(1, menuItemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new MenuItem(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getBoolean("active"),
                        resultSet.getDate("date_of_launch"),
                        resultSet.getString("category"),
                        resultSet.getBoolean("free_delivery")
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
