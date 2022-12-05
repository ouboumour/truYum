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
        final String QUERY = "INSERT INTO carts(item_id, user_id) VALUES (?, ?)";

        try (Connection conn = ConnectionHandler.getConnection();
             PreparedStatement ps = conn.prepareStatement(QUERY)
        ) {
            ps.setLong(1, menuItemId);
            ps.setLong(2, userId);
            ps.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
        List<MenuItem> menuItemList = new ArrayList<>();
        final String QUERY = "SELECT " +
                             "       mi.id, " +
                             "       mi.name, " +
                             "       mi.free_delivery, " +
                             "       mi.price " +
                             "FROM menu_items mi " +
                             "INNER JOIN carts c2 on mi.id = c2.item_id " +
                             "INNER JOIN users u on c2.user_id = u.id " +
                             "WHERE u.id = ?;";

        try (Connection conn = ConnectionHandler.getConnection();
             PreparedStatement ps = conn.prepareStatement(QUERY)
        ) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                throw new CartEmptyException(String.format("The user number %d's card is empty", userId));
            }
            while (rs.next()) {
                menuItemList.add(new MenuItem(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBoolean("free_delivery"),
                        rs.getFloat("price")
                ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return menuItemList;
    }

    @Override
    public void removeCartItem(long userId, long menuItemId) {
        final String QUERY = "DELETE FROM carts WHERE user_id = ? AND item_id = ?";
        try(Connection connection = ConnectionHandler.getConnection();
            PreparedStatement ps = connection.prepareStatement(QUERY);
        ) {
            ps.setLong(1, userId);
            ps.setLong(2, menuItemId);
            ps.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
