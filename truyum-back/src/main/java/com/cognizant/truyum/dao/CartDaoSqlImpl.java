package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoSqlImpl implements CartDao{
    @Override
    public void addCartItem(long userId, long menuItemId) {
        try {
            Connection conn = ConnectionHandler.getConnection();

            String sqlS = "INSERT INTO carts(item_id, user_id) " +
                          "VALUES (?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlS);

            preparedStatement.setLong(1, menuItemId);
            preparedStatement.setLong(2, userId);

            preparedStatement.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
        List<MenuItem> menuItemList = new ArrayList<>();
        try {
            Connection conn = ConnectionHandler.getConnection();

            String sqlS = "SELECT " +
                          "       mi.id, " +
                          "       mi.name, " +
                          "       mi.free_delivery, " +
                          "       mi.price " +
                          "FROM menu_items mi " +
                          "INNER JOIN carts c2 on mi.id = c2.item_id " +
                          "INNER JOIN users u on c2.user_id = u.id " +
                          "WHERE u.id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlS);

            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new CartEmptyException(String.format("The card is empty for the user %d", userId));
            }
            while (resultSet.next()) {
                 menuItemList.add(new MenuItem(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                         resultSet.getBoolean("free_delivery"),
                        resultSet.getFloat("price")
                ));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return menuItemList;
    }

    @Override
    public void removeCartItem(long userId, long menuItemId) {
        try {
            Connection connection = ConnectionHandler.getConnection();
            String sqlS = "DELETE FROM carts WHERE user_id = ? AND item_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlS);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, menuItemId);

            preparedStatement.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
