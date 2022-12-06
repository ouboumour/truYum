package com.cognizant.truyum.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {
    private static Connection con = null;
    private static Properties props = new Properties();

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        try(FileInputStream fis = new FileInputStream("truyum-back/src/main/resources/connection.properties")) {
            props.load(fis);

            // load the Driver Class
            Class.forName(props.getProperty("driver"));

            // create the connection now
            con = DriverManager.getConnection(
                    props.getProperty("connection-url"),
                    props.getProperty("user"),
                    props.getProperty("password"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
